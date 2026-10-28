import { createRouter, createWebHashHistory } from 'vue-router';
import { useAuth } from '../stores/auth';
import SignIn from '../components/SignIn.vue';
import Settings from '../components/Settings.vue';
import MyDashboard from '../components/MyDashboard.vue';

// Dynamic imports for module components
import Alerts from '../components/Alerts.vue';
import AlertDetail from '../components/AlertDetail.vue';
import AlertRules from '../components/AlertRules.vue';
import AlertRuleDetail from '../components/AlertRuleDetail.vue';
import AlertNotifications from '../components/AlertNotifications.vue';
import AlertNotificationDetail from '../components/AlertNotificationDetail.vue';
import AlertAcknowledgements from '../components/AlertAcknowledgements.vue';
import AlertAcknowledgementDetail from '../components/AlertAcknowledgementDetail.vue';

const routes = [
  {
    path: '/',
    name: 'home',
    redirect: '/dashboard'
  },
  {
    path: '/dashboard',
    name: 'Dashboard',
    component: MyDashboard
  },
  
  // Dynamic module routes
  {
    path: '/alerts',
    name: 'Alerts',
    component: Alerts
  },
  {
    path: '/alert/:id',
    name: 'AlertDetail',
    component: AlertDetail,
    props: true
  },
  {
    path: '/alertRules',
    name: 'AlertRules',
    component: AlertRules
  },
  {
    path: '/alertRule/:id',
    name: 'AlertRuleDetail',
    component: AlertRuleDetail,
    props: true
  },
  {
    path: '/alertNotifications',
    name: 'AlertNotifications',
    component: AlertNotifications
  },
  {
    path: '/alertNotification/:id',
    name: 'AlertNotificationDetail',
    component: AlertNotificationDetail,
    props: true
  },
  {
    path: '/alertAcknowledgements',
    name: 'AlertAcknowledgements',
    component: AlertAcknowledgements
  },
  {
    path: '/alertAcknowledgement/:id',
    name: 'AlertAcknowledgementDetail',
    component: AlertAcknowledgementDetail,
    props: true
  },

  // Core application routes
  {
    path: '/signin',
    name: 'SignIn',
    component: SignIn
  },
//  {
//    path: '/users',
//    name: 'Users',
//    component: Users,
//    meta: { requiresAuth: true }
//  },
  {
    path: '/settings',
    name: 'Settings',
    component: Settings,
    meta: { requiresAuth: true }
  },
  {
    path: '/:pathMatch(.*)*',
    redirect: '/dashboard'
  }
];

const router = createRouter({
  history: createWebHashHistory(import.meta.env.BASE_URL),
  routes,
  scrollBehavior(to, from, savedPosition) {
    return savedPosition || { top: 0 };
  }
});

// Navigation guard for authentication
router.beforeEach((to, from, next) => {
  const auth = useAuth();
  
  if (to.meta.requiresAuth && !auth.isAuthenticated) {
    next({ name: 'SignIn', query: { redirect: to.fullPath } });
  } else if (to.name === 'SignIn' && auth.isAuthenticated) {
    next({ name: 'Dashboard' });
  } else {
    next();
  }
});

export default router;
