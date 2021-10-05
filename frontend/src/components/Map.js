import React, { useState } from "react"
import { MapContainer, TileLayer } from "react-leaflet"
import MarkerCluster from "./MarkerCluster"
import "leaflet/dist/leaflet.css"
import L from "leaflet"

const position = [48.807, 33.838]

const Map = (props) => {
  const [map, setMap] = useState(null)

  delete L.Icon.Default.prototype._getIconUrl

  L.Icon.Default.mergeOptions({
    iconRetinaUrl: require("leaflet/dist/images/marker-icon-2x.png"),
    iconUrl: require("leaflet/dist/images/marker-icon.png"),
    shadowUrl: require("leaflet/dist/images/marker-shadow.png"),
  })

  return (
    <MapContainer center={position} zoom={3} scrollWheelZoom={false} style={{ height: "60vh" }} whenCreated={setMap}>
      <TileLayer attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors' url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png" />
      <MarkerCluster map={map} markers={props.markers} />
    </MapContainer>
  )
}

export default Map
