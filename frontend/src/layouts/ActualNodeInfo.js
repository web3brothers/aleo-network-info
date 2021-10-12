import { useEffect, useState } from "react"
import { useParams } from "react-router-dom"
import Page from "./Page"
import Axios from "axios"
import calculateUptime from "../utils/UptimeCalculator.js"

function ActualNodeInfo() {
  const [isLoading, setIsLoading] = useState(true)
  const [data, setData] = useState({
    ip: "",
    blockCount: "",
    bootNodeBlockCount: "",
    nodeStat: {
      connections: {
        allAccepted: "",
        allInitiated: "",
        allRejected: "",
        averageDuration: "",
        connectedPeers: "",
        connectingPeers: "",
        disconnectedPeers: "",
      },
    },
    nodeInfo: {
      syncing: "",
      launched: "",
      version: "",
      nodeType: "",
    },
  })
  const { ip } = useParams()

  useEffect(() => {
    async function fetchData() {
      try {
        console.log(`ip is ${ip}`)
        let response = await Axios.get(`/api/v1/node/${ip}/actual-info`)
        setData(response.data)
        setIsLoading(false)
      } catch (e) {
        console.error(e)
      }
    }
    fetchData()
  }, [ip])

  if (isLoading) {
    return (
      <Page title="...">
        <div>Loading...</div>
      </Page>
    )
  }

  function getNodeType(nodeInfo) {
    if (nodeInfo.miner) {
      return "Miner"
    } else if (nodeInfo.nodeType === "SyncProvider") {
      return "Bootnode"
    } else {
      return "Fullnode"
    }
  }

  return (
    <Page>
      <h2>IP: {data.ip}</h2>
      <table class="table">
        <thead>
          <tr>
            <th scope="col"></th>
            <th scope="col">Network Block Height</th>
            <th scope="col">Node's Block Height</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td className="fst-italic fs-6">Received from synced bootnode {data.bootNodeIp}</td>
            <td>{data.bootNodeBlockCount}</td>
            <td>{data.blockCount}</td>
          </tr>
        </tbody>
      </table>
      <div className="card mb-3">
        <div className="card-header">Node info</div>
        <div className="card-body">
          <table className="table card-text">
            <tbody>
              <tr>
                <td>Node type</td>
                <td>{getNodeType(data.nodeInfo)}</td>
              </tr>
              <tr>
                <td>If the node currently syncing</td>
                <td>{data.nodeInfo.syncing ? "Yes" : "No"}</td>
              </tr>
              <tr>
                <td>Uptime</td>
                <td>{calculateUptime(data.nodeInfo.launched)}</td>
              </tr>
              <tr>
                <td>Version</td>
                <td>{data.nodeInfo.version}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
      <div className="card">
        <div className="card-header">Node statistics</div>
        <div className="card-body">
          <table className="table card-text">
            <tbody>
              <tr>
                <td>The number of connection requests the node has received</td>
                <td>{data.nodeStat.connections.allAccepted}</td>
              </tr>
              <tr>
                <td>The number of connection requests the node has made</td>
                <td>{data.nodeStat.connections.allInitiated}</td>
              </tr>
              <tr>
                <td>The number of connection requests the node has rejected</td>
                <td>{data.nodeStat.connections.allRejected}</td>
              </tr>
              <tr>
                <td>The average connection duration in seconds</td>
                <td>{data.nodeStat.connections.averageDuration}</td>
              </tr>
              <tr>
                <td>The number of currently connected peers</td>
                <td>{data.nodeStat.connections.connectedPeers}</td>
              </tr>
              <tr>
                <td>The number of currently connecting peers</td>
                <td>{data.nodeStat.connections.connectingPeers}</td>
              </tr>
              <tr>
                <td>The number of known disconnected peers</td>
                <td>{data.nodeStat.connections.disconnectedPeers}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </Page>
  )
}

export default ActualNodeInfo
