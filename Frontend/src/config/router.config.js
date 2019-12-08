import {BasicLayout, PageView, RouteView, UserLayout} from '@/components/layouts'

export const asyncRouterMap = [
  {
    path: '/',
    component: BasicLayout,
    meta: {title: '首页'},
    redirect: '/project',
    children: [
      {
        path: '/project',
        name: 'project',
        component: () => import(/* webpackChunkName: "project" */ '../views/project/Project'),
        meta: {title: '项目列表', icon: 'table', permission: ['project']}
      },
      {
        path: '/sale',
        name: 'sale',
        // component: () => import(/* webpackChunkName: "project" */ '../views/project/Project'),
        component: PageView,
        meta: {title: '销售部', icon: 'customer-service', permission: ['sale']},
        children: [
          {
            path: '/product',
            name: 'product',
            component: () => import(/* webpackChunkName: "Implement" */ '../views/sale/product'),
            meta: {title: '商品展示'}
          },
          {
            path: '/salePolicy',
            name: 'salePolicy',
            component: () => import(/* webpackChunkName: "Implement" */ '../views/sale/salePolicy'),
            meta: {title: '销售政策管理'}
          },
          {
            path: '/customerInfo',
            name: 'customerInfo',
            component: () => import(/* webpackChunkName: "Implement" */ '../views/sale/customerInfo'),
            meta: {title: '客户信息管理'}
          },
          {
            path: '/order',
            name: 'order',
            component: () => import(/* webpackChunkName: "Implement" */ '../views/sale/order'),
            meta: {title: '订货管理'}
          },
          {
            path: '/return',
            name: 'return',
            component: () => import(/* webpackChunkName: "Implement" */ '../views/sale/return'),
            meta: {title: '退货管理'}
          }
        ]
      },
      {
        path: '/finance',
        name: 'finance',
        component: PageView,
        meta: {title: '财务部', icon: 'pay-circle', permission: ['finance']},
        children: [
          {
            path: '/gathering',
            name: 'gathering',
            component: () => import(/* webpackChunkName: "Implement" */ '../views/finance/gathering'),
            meta: {title: '收款管理'}
          },
          {
            path: '/refund',
            name: 'refund',
            component: () => import(/* webpackChunkName: "Implement" */ '../views/finance/return'),
            meta: {title: '退款管理'}
          },
          {
            path: '/ingredient',
            name: 'ingredient',
            component: () => import(/* webpackChunkName: "Implement" */ '../views/finance/ingredient'),
            meta: {title: '原料采买管理'}
          },
          {
            path: '/statistics',
            name: 'statistics',
            component: () => import(/* webpackChunkName: "Implement" */ '../views/finance/statistics'),
            meta: {title: '收支统计'}
          }
        ]
      },
      {
        path: '/ingredient',
        name: 'ingredient',
        component: PageView,
        meta: {title: '原材料库', icon: 'shopping-cart', permission: ['ingredient']},
        children: [
          {
            path: '/sum',
            name: 'sum',
            component: () => import(/* webpackChunkName: "Implement" */ '../views/ingredient/sum'),
            meta: {title: '原材料总表'}
          },
          {
            path: '/ingredientInventory',
            name: 'ingredientInventory',
            component: () => import(/* webpackChunkName: "Implement" */ '../views/ingredient/ingredientInventory'),
            meta: {title: '原材料库存表'}
          },
          {
            path: '/in',
            name: 'in',
            component: () => import(/* webpackChunkName: "Implement" */ '../views/ingredient/in'),
            meta: {title: '原材料进货表'}
          },
          {
            path: '/out',
            name: 'out',
            component: () => import(/* webpackChunkName: "Implement" */ '../views/ingredient/out'),
            meta: {title: '原材料出库表'}
          },
        ]
      },
      {
        path: '/plan',
        name: 'plan',
        component: PageView,
        meta: {title: '生产计划科', icon: 'calendar', permission: ['plan']},
        children: [
          {
            path: '/productIngredient',
            name: 'productIngredient',
            component: () => import(/* webpackChunkName: "Implement" */ '../views/plan/product'),
            meta: {title: '商品原料管理'}
          },
          {
            path: '/productIngredientEdit/:id',
            name: 'productIngredientEdit',
            props:true,
            hidden:true,
            component: () => import(/* webpackChunkName: "Implement" */ '../views/plan/productIngredientEdit'),
            meta: {title: '配料管理'}
          },
          {
            path: '/producePlan',
            name: 'producePlan',
            component: () => import(/* webpackChunkName: "Implement" */ '../views/plan/producePlan'),
            meta: {title: '生产计划制定'}
          },
          {
            path: '/producePlanRound/:id',
            name: 'producePlanRound',
            props:true,
            hidden:true,
            component: () => import(/* webpackChunkName: "Implement" */ '../views/plan/producePlanRound'),
            meta: {title: '批次分配'}
          }
        ]
      },
      {
        path: '/produceDepartment',
        name: 'produceDepartment',
        component: PageView,
        meta: {title: '生产车间部门', icon: 'shop', permission: ['workshop']},
        children: [
          {
            path: '/factory',
            name: 'factory',
            component: () => import(/* webpackChunkName: "Implement" */ '../views/produceDepartment/factory'),
            meta: {title: '工厂管理'}
          },
          {
            path: '/department',
            name: 'department',
            component: () => import(/* webpackChunkName: "Implement" */ '../views/produceDepartment/department'),
            meta: {title: '车间管理'}
          },
          {
            path: '/team',
            name: 'team',
            component: () => import(/* webpackChunkName: "Implement" */ '../views/produceDepartment/team'),
            meta: {title: '班组管理'}
          },
          {
            path: '/temRound/:id',
            name: 'teamRound',
            props:true,
            hidden:true,
            component: () => import(/* webpackChunkName: "Implement" */ '../views/produceDepartment/teamRound'),
            meta: {title: '批次分配'}
          },
          {
            path: '/peiliao/:id',
            name: 'peiliao',
            props:true,
            hidden:true,
            component: () => import(/* webpackChunkName: "Implement" */ '../views/produceDepartment/peiliao'),
            meta: {title: '所需配料清单'}
          },
          {
            path: '/producing',
            name: 'producing',
            component: () => import(/* webpackChunkName: "Implement" */ '../views/produceDepartment/producing'),
            meta: {title: '成品检验'}
          },
        ]
      },
      {
        path: '/finish',
        name: 'finish',
        component: PageView,
        meta: {title: '成品库部门', icon: 'home', permission: ['finish']},
        children: [
          {
            path: '/batch',
            name: 'batch',
            component: () => import(/* webpackChunkName: "Implement" */ '../views/finish/batch'),
            meta: {title: '成品批次管理'}
          }
        ]
      },
      {
        path: '/staff',
        name: 'staff',
        component: RouteView,
        meta: {title: '人事管理', icon: 'usergroup-add', permission: ['stuff']},
        children: [
          {
            path: '/servicer',
            name: 'servicer',
            component: () => import(/* webpackChunkName: "Implement" */ '../views/staff/servicer'),
            meta: {title: '业务人员管理'}
          },
          {
            path: '/manager',
            name: 'manager',
            component: () => import(/* webpackChunkName: "Implement" */ '../views/staff/manager'),
            meta: {title: '部门管理者管理'}
          }
          ]
      },
      {
        path: '/users',
        name: 'users',
        component: PageView,
        meta: {title: '用户管理', icon: 'user', permission: ['stuff']},
        children: [
          {
            path: '/addUser',
            name: 'addUser',
            component: () => import(/* webpackChunkName: "Implement" */ '../views/users/addUser'),
            meta: {title: '新建用户'}
          }
        ]
      },
      {
        path: '/project/create',
        name: 'projectCreate',
        component: () => import(/* webpackChunkName: "project" */ '../views/project/ProjectDetail'),
        hidden: true,
        meta: {title: '新建项目', permission: ['sale']}
      },
      {
        path: '/project/edit',
        name: 'projectEdit',
        component: () => import(/* webpackChunkName: "project" */ '../views/project/ProjectDetail'),
        hidden: true,
        meta: {title: '新建项目', permission: ['sale']}
      },
      {
        path: '/detail',
        component: PageView,
        meta: {title: '项目详情', icon: 'profile', permission: ['sale', 'project_selected']},
        children: [
          {
            path: '',
            name: 'detail',
            component: () => import(/* webpackChunkName: "detail" */ '../views/detail/Detail'),
            meta: {title: '订单详情'},

          },
          {
            path: '/GPS',
            name: 'gps',
            props: true,
            hidden: true,
            component: () => import(/* webpackChunkName: "detail" */ '../views/detail/GPS'),
            meta: {title: '项目定位'},
          },
        ]
      },
      {
        path: '/setting',
        component: PageView,
        meta: {title: '设置', icon: 'setting', permission: ['setting']},
        children: [
          {
            path: '/userPermissions',
            name: 'settingUserPermissions',
            component: RouteView,
            meta: {title: '用户与权限管理'},
            children: [
              {
                path: '/users',
                name: 'settingUsers',
                props: true,
                component: () => import(/* webpackChunkName: "Implement" */ '../views/setting/userPermissions/users'),
                meta: {title: '用户管理'}
              },
              {
                path: '/roles',
                name: 'settingsRoles',
                props: true,
                component: () => import(/* webpackChunkName: "Implement" */ '../views/setting/userPermissions/roles'),
                meta: {title: '角色管理'}
              },
              {
                path: '/permissions',
                name: 'settingPermissions',
                props: true,
                component: () => import(/* webpackChunkName: "Implement" */ '../views/setting/userPermissions/permissions'),
                meta: {title: '权限管理'}
              }
            ]
          },
          {
            path: 'projectSettings',
            name: 'ProjectSettings',
            component: () => import(/* webpackChunkName: "Implement" */ '../views/setting/projectSettings'),
            meta: {title: '项目设置', permission: ['setting']}
          },
          {
            path: '/system',
            component: RouteView,
            meta: {title: '系统设置'},
            children: [
              {
                path: '/projectDetailSetting',
                name: 'ProjectDetailSetting',
                component: () => import(/* webpackChunkName: "Implement" */ '../views/setting/ProjectDetailSetting'),
                meta: {title: '项目概况设置'}
              },
            ]
          },

        ]
      },
      {
        path: '/help',
        name: 'help',
        component: PageView,
        meta: {title: '帮助', icon: 'question-circle', permission: ['help']}
      },
    ]
  },
  {
    path: '*', redirect: '/404', hidden: true
  }
]

/**
 * 基础路由
 * @type { *[] }
 */
export const constantRouterMap = [
  {
    path: '/user',
    component: UserLayout,
    redirect: '/user/login',
    hidden: true,
    children: [
      {
        path: 'login',
        name: 'login',
        component: () => import(/* webpackChunkName: "user" */ '@/views/user/Login')
      }
    ]
  },
  {
    path: '/404',
    component: () => import(/* webpackChunkName: "fail" */ '@/views/exception/404')
  }
]
