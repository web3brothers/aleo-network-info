import React from "react"
import { useEffect, useState } from "react"
import ReactEcharts from "echarts-for-react"
import Axios from "axios"
import { Card, Container, Row, Col } from "react-bootstrap"
import Map from "../components/Map.js"
import Page from "./Page.js"
import NodesTypeSelector from "../components/NodesTypeSelector.js"

function PeersDashboard() {
  const [nodesType, setNodesType] = useState("ALL")

  const initialState = {
    lastCollectedOn: "",
    aggregationByCities: [],
    aggregationByRoles: {
      totalNodes: "",
      miners: "",
      fullNodes: "",
      bootNodes: "",
      notReachable: "",
    },
    aggregationByProviders: {
      providers: [],
    },
    aggregationByVersions: {
      versions: [],
    },
    bootNodesStatus: {
      bootNodesStatus: [],
    },
  }

  const [summary, setSummary] = useState(initialState)

  useEffect(() => {
    async function fetchData() {
      try {
        let data = await Axios.get(`/api/v1/network-summary?nodes_type=${nodesType}`)
        setSummary(data.data)
      } catch (e) {
        console.log("Error occured", e)
      }
    }
    fetchData()
  }, [nodesType])

  function prepareProvidersDataForPieChart(aggregationByProviders) {
    return aggregationByProviders.providers.reduce((acc, v) => {
      acc.push({ value: v.percents, name: `${v.org} - ${v.nodes} hosts (${v.percents}%)` })
      return acc
    }, [])
  }

  function prepareVersionsDataForPieChart(aggregationByVersions) {
    return aggregationByVersions.versions.reduce((acc, v) => {
      acc.push({ value: v.nodes, name: v.version })
      return acc
    }, [])
  }

  function prepareMarkersForMap(aggregationByCities) {
    return aggregationByCities.reduce((acc, v) => {
      acc.push({
        position: { lat: v.lat, lng: v.lon },
        text: `${v.city}, ${v.org} - ${v.nodes} nodes`,
      })
      return acc
    }, [])
  }

  return (
    <Page title="Dashboard">
      <Container fluid>
        <Row>
          <Col lg="12" sm="6">
            <NodesTypeSelector nodesType={nodesType} setNodesType={setNodesType} />
          </Col>
        </Row>
        <Row>
          <Col lg="12" sm="6">
            <p className="card-category custom-label-text">
              Collected on: <span>{summary.lastCollectedOn}</span>
            </p>
          </Col>
        </Row>
        <Row>
          <Col lg="1"></Col>
          <Col lg="2" sm="6">
            <Card className="card-stats">
              <Card.Body>
                <Row>
                  <Col xs="3">
                    <div className="icon-big text-center icon-warning">
                      <i className="nc-icon nc-chart text-warning"></i>
                    </div>
                  </Col>
                  <Col xs="9">
                    <div className="numbers">
                      <p className="card-category">Total hosts</p>
                      <Card.Title as="h4">{summary.aggregationByRoles.totalNodes}</Card.Title>
                    </div>
                  </Col>
                </Row>
              </Card.Body>
            </Card>
          </Col>
          <Col lg="2" sm="6">
            <Card className="card-stats">
              <Card.Body>
                <Row>
                  <Col xs="3">
                    <div className="icon-big text-center icon-warning">
                      <i className="nc-icon nc-light-3 text-success"></i>
                    </div>
                  </Col>
                  <Col xs="9">
                    <div className="numbers">
                      <p className="card-category">Miners</p>
                      <Card.Title as="h4">{summary.aggregationByRoles.miners}</Card.Title>
                    </div>
                  </Col>
                </Row>
              </Card.Body>
            </Card>
          </Col>
          <Col lg="2" sm="6">
            <Card className="card-stats">
              <Card.Body>
                <Row>
                  <Col xs="3">
                    <div className="icon-big text-center icon-warning">
                      <i className="nc-icon nc-vector text-danger"></i>
                    </div>
                  </Col>
                  <Col xs="9">
                    <div className="numbers">
                      <p className="card-category">Full nodes</p>
                      <Card.Title as="h4">{summary.aggregationByRoles.fullNodes}</Card.Title>
                    </div>
                  </Col>
                </Row>
              </Card.Body>
            </Card>
          </Col>
          <Col lg="2" sm="6">
            <Card className="card-stats">
              <Card.Body>
                <Row>
                  <Col xs="3">
                    <div className="icon-big text-center icon-warning">
                      <i className="nc-icon nc-vector text-danger"></i>
                    </div>
                  </Col>
                  <Col xs="9">
                    <div className="numbers">
                      <p className="card-category">Boot nodes</p>
                      <Card.Title as="h4">{summary.aggregationByRoles.bootNodes}</Card.Title>
                    </div>
                  </Col>
                </Row>
              </Card.Body>
            </Card>
          </Col>
          <Col lg="2" sm="6">
            <Card className="card-stats">
              <Card.Body>
                <Row>
                  <Col xs="3">
                    <div className="icon-big text-center icon-warning">
                      <i className="nc-icon nc-favourite-28 text-primary"></i>
                    </div>
                  </Col>
                  <Col xs="9">
                    <div className="numbers">
                      <p className="card-category">Not reachable</p>
                      <Card.Title as="h4">{summary.aggregationByRoles.notReachable}</Card.Title>
                    </div>
                  </Col>
                </Row>
              </Card.Body>
            </Card>
          </Col>
          <Col lg="1"></Col>
        </Row>
        <Row>
          <Col md="1"></Col>
          <Col md="10">
            <div id="chartHostsByProviders" className="mt-3 mb-n4">
              <ReactEcharts
                style={{ height: "450px", width: "100%" }}
                option={{
                  title: {
                    text: "Decentralization",
                    subtext: "Hosts by providers",
                    left: "center",
                  },
                  tooltip: {
                    trigger: "item",
                  },
                  series: [
                    {
                      name: "Provider",
                      type: "pie",
                      radius: "60%",
                      data: prepareProvidersDataForPieChart(summary.aggregationByProviders),
                      emphasis: {
                        itemStyle: {
                          shadowBlur: 10,
                          shadowOffsetX: 0,
                          shadowColor: "rgba(0, 0, 0, 0.5)",
                        },
                      },
                    },
                  ],
                }}
              />
            </div>
          </Col>
          <Col md="1"></Col>
        </Row>
        <Row>
          <Col md="12">
            <Map markers={prepareMarkersForMap(summary.aggregationByCities)} />
          </Col>
        </Row>
        <Row>
          <Col md="12">
            <div id="pieByVersions" className="mt-3">
              <ReactEcharts
                style={{ height: "450px", width: "100%" }}
                option={{
                  title: {
                    text: "Versions",
                    subtext: "Hosts by SnarkOS versions",
                    left: "center",
                  },
                  tooltip: {
                    trigger: "item",
                  },
                  series: [
                    {
                      name: "SnarkOS Version",
                      type: "pie",
                      radius: "60%",
                      data: prepareVersionsDataForPieChart(summary.aggregationByVersions),
                      emphasis: {
                        itemStyle: {
                          shadowBlur: 10,
                          shadowOffsetX: 0,
                          shadowColor: "rgba(0, 0, 0, 0.5)",
                        },
                      },
                    },
                  ],
                }}
              />
            </div>
          </Col>
        </Row>
      </Container>
    </Page>
  )
}

export default PeersDashboard
