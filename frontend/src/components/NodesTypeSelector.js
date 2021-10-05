import { ButtonGroup, ToggleButton } from "react-bootstrap"

function NodesTypeSelector(props) {
  const nodesTypes = [
    { name: "All", value: "ALL" },
    { name: "Miners", value: "MINERS" },
    { name: "Full nodes", value: "FULL_NODES" },
    { name: "Boot nodes", value: "BOOT_NODES" },
  ]

  console.log("nodesType from props:" + props.nodesType)

  return (
    <ButtonGroup className="mb-2">
      {nodesTypes.map((radio, idx) => (
        <ToggleButton key={idx} id={`radio-${idx}`} type="radio" variant="secondary" name="radio" value={radio.value} checked={props.nodesType === radio.value} onChange={(e) => props.setNodesType(e.currentTarget.value)}>
          {radio.name}
        </ToggleButton>
      ))}
    </ButtonGroup>
  )
}

export default NodesTypeSelector
