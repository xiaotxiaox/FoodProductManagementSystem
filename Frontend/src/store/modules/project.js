const project = {
  state: {
    selected: null
  },
  mutations: {
    SET_PROJECT: (state, project) => {
      state.selected = project
    }
  }
}

export default project
