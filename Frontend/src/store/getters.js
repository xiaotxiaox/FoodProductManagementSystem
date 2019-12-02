const getters = {
  device: state => state.app.device,
  theme: state => state.app.theme,
  color: state => state.app.color,
  multiTab: state => state.app.multiTab,
  username: state => state.user.username,
  role: state => state.user.role,
  addRouters: state => state.permission.addRouters,
  projectSelected: state => state.project.selected
}

export default getters
