export function looseEqual (a, b) {
  // eslint-disable-next-line eqeqeq
  return a == b || (
    isObject(a) && isObject(b) ? JSON.stringify(a) === JSON.stringify(b) : false
  )
}