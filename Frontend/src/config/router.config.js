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
        meta: {title: '项目列表', icon: 'table', permission: ['sale']}
      },
      {
        path: '/sale',
        name: 'sale',
        // component: () => import(/* webpackChunkName: "project" */ '../views/project/Project'),
        component: RouteView,
        meta: {title: '销售部', icon: 'customer-service', permission: ['sale']},
        children: [
          {
            path: '/customerInfo',
            name: 'customerInfo',
            component: () => import(/* webpackChunkName: "Implement" */ '../views/sale/customerInfo'),
            meta: {title: '客户信息管理'}
          },
          {
            path: '/salePolicy',
            name: 'salePolicy',
            component: () => import(/* webpackChunkName: "Implement" */ '../views/sale/salePolicy'),
            meta: {title: '销售政策管理'}
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
        component: RouteView,
        // component: () => import(/* webpackChunkName: "project" */ '../views/project/Project'),
        meta: {title: '财务部', icon: 'pay-circle', permission: ['sale']},
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
        path: '3',
        name: '3',
        // component: () => import(/* webpackChunkName: "project" */ '../views/project/Project'),
        meta: {title: '成品库部门', icon: 'home', permission: ['sale']}
      },
      {
        path: '4',
        name: '4',
        // component: () => import(/* webpackChunkName: "project" */ '../views/project/Project'),
        meta: {title: '生产计划科', icon: 'calendar', permission: ['sale']}
      },
      {
        path: '5',
        name: '5',
        // component: () => import(/* webpackChunkName: "project" */ '../views/project/Project'),
        meta: {title: '生产车间部门', icon: 'shop', permission: ['sale']}
      },
      {
        path: '6',
        name: '6',
        // component: () => import(/* webpackChunkName: "project" */ '../views/project/Project'),
        meta: {title: '原材料库', icon: 'shopping-cart', permission: ['sale']}
      },
      {
        path: '/staff',
        name: 'staff',
        component: RouteView,
        meta: {title: '人事管理', icon: 'usergroup-add', permission: ['sale']},
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
            meta: {title: '项目概况'},

          },
          {
            path: '/GPS',
            name: 'gps',
            props: true,
            hidden: true,
            component: () => import(/* webpackChunkName: "detail" */ '../views/detail/GPS'),
            meta: {title: '项目定位'},
          },
          // {
          //   path: '/map',
          //   name: 'map',
          //   component: () => import(/* webpackChunkName: "detail" */ '../views/detail/map'),
          //   meta: { title: 'hahahah' },
          // },
          {
            path: 'contact',
            name: 'contact',
            component: () => import(/* webpackChunkName: "detail" */ '../views/detail/Contact'),
            meta: {title: '通讯录'}
          }
        ]
      },
      {
        path: '/decision',
        name: 'decision',
        component: () => import(/* webpackChunkName: "decision" */ '../views/decision/Decision'),
        meta: {title: '项目决策', icon: 'audit', permission: ['decision', 'project_selected']}
      },
      {
        path: '/decision/edit:id',
        name: 'DecisionEdit',
        hidden: true,
        prop: true,
        component: () => import(/* webpackChunkName: "decision" */ '../views/decision/DecisionEdit'),
        meta: {title: '项目决策编辑', permission: ['decision', 'project_selected']}
      },
      {
        path: '/decision/:id',
        name: 'decisionDetail',
        component: () => import(/* webpackChunkName: "decision" */ '../views/decision/DecisionDetail'),
        props: true,
        hidden: true,
        meta: {title: '决策详情', permission: ['decision', 'project_selected']}
      },
      {
        path: 'decisionFile/:id',
        name: 'DecisionFile',
        props: true,
        hidden: true,
        component: () => import(/* webpackChunkName: "setting" */ '../views/decision/DecisionFile'),
        meta: {title: '项目决策文件详情', permission: ['decision', 'project_selected']}
      },
      {
        path: '/implement',
        name: 'implement',
        component: PageView,
        meta: {title: '项目实施', icon: 'tool', permission: ['implement', 'project_selected']},
        children: [
          {
            path: 'application',
            name: 'implementApplication',
            component: () => import(/* webpackChunkName: "Implement" */ '../views/implement/application/Application'),
            meta: {title: '报批报建'}
          },
          {
            path: 'application/Edit',
            name: 'implementApplicationEdit',
            hidden: true,
            component: () => import(/* webpackChunkName: "Implement" */ '../views/implement/application/ApplicationEdit'),
            meta: {title: '报批报建修改'}
          },
          {
            path: 'application/:id',
            name: 'implementApplicationDetail',
            component: () => import(/* webpackChunkName: "Implement" */ '../views/implement/application/ApplicationDetail'),
            props: true,
            hidden: true,
            meta: {title: '报批报建详情'}
          },
          {
            path: 'bid',
            name: 'implementBid',
            component: () => import(/* webpackChunkName: "Implement" */ '../views/implement/bid/Bid'),
            meta: {title: '招标采购'}
          },
          {
            path: 'bid/:id',
            name: 'BidFileDetail',
            props: true,
            hidden: true,
            component: () => import(/* webpackChunkName: "Implement" */ '../views/implement/bid/BidFileDetail'),
            meta: {title: '招标文件'}
          },
          {
            path: 'winBid/：id',
            name: 'WinBidFileDetail',
            props: true,
            hidden: true,
            component: () => import(/* webpackChunkName: "Implement" */ '../views/implement/bid/WinBidFileDetail'),
            meta: {title: '中标文件'}
          },
          {
            path: 'design',
            name: 'implementDesign',
            component: () => import(/* webpackChunkName: "Implement" */ '../views/implement/design/Design'),
            meta: {title: '设计管理'}
          },
          {
            path: '/paymentDetail',
            name: 'paymentDetail',
            props: true,
            hidden: true,
            component: () => import(/* webpackChunkName: "setting" */ '../views/implement/design/PaymentDetail'),
            meta: {title: '设计管理支付详情'}
          },
          {
            path: 'design/:id',
            name: 'implementDesignDetail',
            props: true,
            hidden: true,
            component: () => import(/* webpackChunkName: "Implement" */ '../views/implement/design/DesignDetail'),
            meta: {title: '设计管理详情'}
          },

          {
            path: 'design/account',
            name: 'implementDesignAccountDetail',
            props: true,
            hidden: true,
            component: () => import(/* webpackChunkName: "Implement" */ '../views/implement/design/DesignAccountDetail'),
            meta: {title: '设计管理记账表'}
          },
          {
            path: 'design/account:id',
            name: 'paymentDetail',
            props: true,
            hidden: true,
            component: () => import(/* webpackChunkName: "Implement" */ '../views/implement/design/PaymentDetail'),
            meta: {title: '支付详情'}
          },
          {
            path: '/invest',
            name: 'implementInvest',
            component: RouteView,
            meta: {title: '投资管理'},
            children: [
              // {
              //   path: 'invest_module',
              //   name: 'investModule',
              //   props: true,
              //   component: () => import(/* webpackChunkName: "Implement" */ '../views/implement/invest/invest_module/invest_module'),
              //   meta: { title: '投资模块分配' }
              // },
              {
                path: 'account',
                name: 'investAccount',
                props: true,
                component: () => import(/* webpackChunkName: "Implement" */ '../views/implement/invest/invest_account/invest_account'),
                meta: {title: '台账'}
              },
              {
                path: 'InvestTable',
                name: 'investTable',
                props: true,
                component: () => import(/* webpackChunkName: "Implement" */ '../views/implement/invest/invest_table/invest_table'),
                meta: {title: '项目总投资表'}
              },
              {
                path: 'InvestTree',
                name: 'investTree',
                props: true,
                component: () => import(/* webpackChunkName: "Implement" */ '../views/implement/invest/invest_tree/invest_new_tree'),
                meta: {title: '动态投资树'}
              },
              {
                path: '/accountChanges/1',
                name: 'accountChangesFile1',
                props: true,
                hidden: true,
                component: () => import(/* webpackChunkName: "setting" */ '../views/implement/invest/invest_account/investChangeDetail1'),
                meta: {title: '变更台账附件详情'}
              },
              {
                path: '/accountProcess',
                name: 'accountProcessDetail',
                props: true,
                hidden: true,
                component: () => import(/* webpackChunkName: "setting" */ '../views/implement/invest/invest_account/AccountProcessDetail'),
                meta: {title: '进度款支付详情'}
              },
              {
                path: '/accountProcessFile',
                name: 'accountProcessFile',
                props: true,
                hidden: true,
                component: () => import(/* webpackChunkName: "setting" */ '../views/implement/invest/invest_account/AccountProcessFile'),
                meta: {title: '中标清单详情'}
              },
              {
                path: '/accountChanges/2',
                name: 'accountChangesFile2',
                props: true,
                hidden: true,
                component: () => import(/* webpackChunkName: "setting" */ '../views/implement/invest/invest_account/investChangeDetail2'),
                meta: {title: '变更台账合同详情'}
              },
              // {
              //   path: 'distribution',
              //   name: 'investDistribution',
              //   props: true,
              //   component: () => import(/* webpackChunkName: "Implement" */ '../views/implement/invest/invest_distribution/invest_distribution'),
              //   meta: { title: '投资分配图表' }
              // },

            ]
          },
          {
            path: '/progress',
            component: RouteView,
            meta: {title: '进度管理'},
            children: [
              {
                path: 'build',
                name: 'implementProgressBuild',
                component: () => import(/* webpackChunkName: "Implement" */ '../views/implement/progress/Build'),
                meta: {title: '形象进度'}
              },
              {
                path: 'claim',
                name: 'implementProgressClaim',
                component: () => import(/* webpackChunkName: "Implement" */ '../views/implement/progress/Claim'),
                meta: {title: '工期索赔'}
              },
              {
                path: 'claimDetail/:id',
                name: 'implementProgressClaimDetail',
                hidden: true,
                props: true,
                component: () => import(/* webpackChunkName: "Implement" */ '../views/implement/progress/ClaimDetail'),
                meta: {title: '附件详情'}
              },
              {
                path: 'gantt',
                name: 'implementProgressGantt',
                props: true,
                component: () => import(/* webpackChunkName: "Implement" */ '../views/implement/progress/Show'),
                meta: {title: '施工进度图'}
              },]
          },
          {
            path: '/quality',
            name: 'quality',
            component: () => import(/* webpackChunkName: "Implement" */ '../views/implement/quality/Quality'),
            meta: {title: '质量管理'}
          },
          {
            path: 'security',
            name: 'implementSecurity',
            component: () => import(/* webpackChunkName: "Implement" */ '../views/implement/security/Security'),
            meta: {title: '安全管理'}
          },
          {
            path: 'securityDetail/:id',
            name: 'implementSecurityDetail',
            hidden: true,
            props: true,
            component: () => import(/* webpackChunkName: "Implement" */ '../views/implement/security/SecurityDetail'),
            meta: {title: '安全管理文件'}
          },
          {
            path: '/contract',
            name: 'contract',
            component: () => import(/* webpackChunkName: "Implement" */ '../views/implement/contract/Contract'),
            meta: {title: '合同管理'}
          },
          {
            path: 'contractChange/:id',
            name: 'ChangeDetail',
            props: true,
            hidden: true,
            component: () => import(/* webpackChunkName: "Implement" */ '../views/implement/contract/ChangeDetail'),
            meta: {title: '工程变更文件'}
          },
          {
            path: 'contractReview/:id',
            name: 'ReviewDetail',
            props: true,
            hidden: true,
            component: () => import(/* webpackChunkName: "Implement" */ '../views/implement/contract/ReviewDetail'),
            meta: {title: '延期审查文件'}
          },
          {
            path: 'contractProject/:id',
            name: 'ContractDetail',
            props: true,
            hidden: true,
            component: () => import(/* webpackChunkName: "Implement" */ '../views/implement/contract/ContractDetail'),
            meta: {title: '工程合同文件'}
          },
          {
            path: 'information',
            name: 'implementInformation',
            component: () => import(/* webpackChunkName: "Implement" */ '../views/implement/information/Information'),
            meta: {title: '信息管理'}
          },
          {
            path: 'information/edit/:id',
            name: 'implementInformationEdit',
            hidden: true,
            component: () => import(/* webpackChunkName: "Implement" */ '../views/implement/information/InformationEdit'),
            meta: {title: '信息管理修改'}
          },
          {
            path: 'information/:id',
            name: 'implementInformationDetail',
            props: true,
            hidden: true,
            component: () => import(/* webpackChunkName: "Implement" */ '../views/implement/information/InformationDetail'),
            meta: {title: '信息管理详情'}
          },
        ]
      },
      {
        path: '/completion',
        name: 'completion',
        component: PageView,
        meta: {title: '项目竣工', icon: 'audit', permission: ['completion', 'project_selected']},
        children: [
          {
            path: '/completionDocument',
            name: 'completionDocument',
            component: () => import(/* webpackChunkName: "Implement" */ '../views/completion/Completion'),
            meta: {title: '竣工文件管理',}
          },
          {
            path: 'DocumentDetail/:id',
            name: 'documentDetail',
            component: () => import(/* webpackChunkName: "Implement" */ '../views/completion/CompletionDetail'),
            props: true,
            hidden: true,
            meta: {title: '竣工文件详情'}
          },
          {
            path: 'else',
            name: 'else',
            component: () => import(/* webpackChunkName: "Implement" */ '../views/completion/elseDetail/Else'),
            meta: {title: '项目竣工详情'}
          },
          {
            path: 'else/Edit',
            name: 'elseEdit',
            hidden: true,
            component: () => import(/* webpackChunkName: "Implement" */ '../views/completion/elseDetail/ElseEdit'),
            meta: {title: '竣工详情修改'}
          },
          {
            path: 'else/:id',
            name: 'elseDetail',
            component: () => import(/* webpackChunkName: "Implement" */ '../views/completion/elseDetail/ElseDetail'),
            props: true,
            hidden: true,
            meta: {title: '项目竣工详情'}
          }
        ]
      },
      {
        path: '/videoTable',
        name: 'VideoTable',
        component: () => import(/* webpackChunkName: "decision" */ '../views/video/VideoTable'),
        meta: {title: '项目视频', icon: 'audit', permission: ['video', 'project_selected']}
      },
      {
        path: '/video:id',
        name: 'video',
        props: true,
        hidden: true,
        component: () => import(/* webpackChunkName: "decision" */ '../views/video/Video'),
        meta: {title: '项目视频编辑', icon: 'audit', permission: ['video', 'project_selected']}
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
