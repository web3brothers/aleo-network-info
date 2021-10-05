import "../src/scss/style.scss"
import React from "react"
import ReactDOM from "react-dom"
import PeersDashboard from "./layouts/PeersDashboard"
import NodesList from "./layouts/NodesList"
import Header from "./layouts/Header"
import { BrowserRouter, Switch, Route } from "react-router-dom"

ReactDOM.render(
  <React.StrictMode>
    <BrowserRouter>
      <Header />
      <Switch>
        <Route path="/" exact>
          <PeersDashboard />
        </Route>
        <Route path="/boot-nodes" exact>
          <NodesList nodesType="BOOT_NODES" />
        </Route>
        <Route path="/miners" exact>
          <NodesList nodesType="MINERS" />
        </Route>
        <Route path="/full-nodes" exact>
          <NodesList nodesType="FULL_NODES" />
        </Route>
      </Switch>
    </BrowserRouter>
  </React.StrictMode>,
  document.getElementById("root")
)
