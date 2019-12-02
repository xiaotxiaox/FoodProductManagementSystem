<template>
  <div ref="map" class="baidu-map"></div>
</template>
<script>
export default {
  props: {
    zoom: {
      type: Number,
      default: 15
    },
    center: {
      type: [Object, String],
      default () {
        return {lng: 116.404, lat: 39.915}
      }
    },
    marker: {
      type: [Object, String],
      default () {
        return {lng: 116.404, lat: 39.915}
      }
    },
    search: ''
  },
  data: () => ({
    ak: 'okFNZHPQ0BfF9Qhq3K1WmrRv',
    BMap: {},
    map: {},
    currentPosition: null
  }),
  methods: {
    init (BMap) {
      let $el = this.$refs.map
      const map = new BMap.Map($el)
      this.map = map

      // 初始化中心位置
      const {getCenterPoint, zoom} = this
      map.centerAndZoom(getCenterPoint(), zoom || 15)

      this.setMarker(this.marker)
      this.$emit('ready', {BMap, map})
    },

    // 初始化地图
    initMap (BMap) {
      this.BMap = BMap
      this.init(BMap)
    },

    // 加载百度地图API
    getMapScript () {
      if (!global.BMap) {
        const ak = this.ak || this._BMap().ak
        global.BMap = {}
        global.BMap._preloader = new Promise((resolve, reject) => {
          global._initBaiduMap = function () {
            resolve(global.BMap)
            global.document.body.removeChild($script)
            global.BMap._preloader = null
            global._initBaiduMap = null
          }
          const $script = document.createElement('script')
          global.document.body.appendChild($script)
          $script.src = `//api.map.baidu.com/api?v=2.0&ak=${ak}&callback=_initBaiduMap`
        })
        return global.BMap._preloader
      } else if (!global.BMap._preloader) {
        return Promise.resolve(global.BMap)
      } else {
        return global.BMap._preloader
      }
    },

    // 地图中心点
    getCenterPoint () {
      const {center, BMap} = this
      switch (typeof center) {
        case 'string':
          return center
        case 'object':
          return new BMap.Point(parseFloat(center.lng), parseFloat(center.lat))
        default: return new BMap.Point(116.404, 39.915)
      }
    },

    // 设置中心点
    setCenter () {
      const {map, getCenterPoint} = this
      map.setCenter(getCenterPoint())
    },

    // 设置Marker
    setMarker (p) {
      const {map, BMap} = this
      map.clearOverlays() // 清除地图上所有覆盖物
      const marker = new BMap.Marker(new BMap.Point(parseFloat(p.lng), parseFloat(p.lat))) // 创建标注
      map.addOverlay(marker) // 将标注添加到地图中
      marker.enableDragging() // 可拖拽
      marker.addEventListener('dragend', () => {
        var p = marker.getPosition()
        this.currentValue = {
          lng: p.lng,
          lat: p.lat
        }
      })
    },


    // 获取当前坐标
    getCurrentPosition (callback) {
      const { BMap } = this
      var _this = this
      let geolocation = new BMap.Geolocation()
      geolocation.getCurrentPosition(function (r) {
        if (this.getStatus() === global.BMAP_STATUS_SUCCESS) {
          _this.currentPosition = r.point
          callback(r)
        } else {
          callback(false)
        }
      }, {enableHighAccuracy: true})
    },

    // 初始化附近
    initNearby (callback) {
      const { BMap, currentPosition, getCurrentPosition } = this
      var geoc = new BMap.Geocoder()

      if (!currentPosition) {
        getCurrentPosition(response => {
          let point = new BMap.Point(parseFloat(response.point.lng), parseFloat(response.point.lat))
          geoc.getLocation(point, function (results) {
            callback(results.surroundingPois)
          }, {
            poiRadius: 10 * 1000,
            numPois: 10
          })
        })
      } else {
        let point = new BMap.Point(parseFloat(currentPosition.lng), parseFloat(currentPosition.lat))
        geoc.getLocation(point, function (results) {
          callback(results)
        })
      }
    },

    // 获取周边信息
    searchNearby (key, callback) {
      const { BMap, map, currentPosition, getCurrentPosition } = this
      var options = {
        onSearchComplete: results => {
          if (local.getStatus() === global.BMAP_STATUS_SUCCESS) {
            var rel = []
            for (var i = 0; i < results.getCurrentNumPois(); i++) {
              rel.push(results.getPoi(i))
            }
            callback(rel)
          }
        }
      }
      var local = new BMap.LocalSearch(map, options)

      if (!currentPosition) {
        getCurrentPosition(response => {
          let point = new BMap.Point(parseFloat(response.point.lng), parseFloat(response.point.lat))
          local.searchNearby(key, point, 20 * 1000)
        })
      } else {
        let point = new BMap.Point(parseFloat(currentPosition.lng), parseFloat(currentPosition.lat))
        local.searchNearby(key, point, 20 * 1000)
      }
    }
  },
  created () {
    const {getMapScript, initMap} = this
    getMapScript().then(initMap) // 加载地图接口，并初始化地图
  }
}

</script>
<style>
.baidu-map {
  width: 100%;
  height: 500px;
  border: 1px solid #eee;
}
</style>


