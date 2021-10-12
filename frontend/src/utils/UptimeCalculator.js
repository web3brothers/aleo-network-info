import humanizeDuration from "humanize-duration"

const calculateUptime = function calculateUptime(launched) {
  if (launched !== null) {
    let startTime = Date.parse(launched)
    let endTime = Date.now()
    return humanizeDuration(endTime - startTime, { largest: 3 })
  }
  return ""
}

export default calculateUptime
