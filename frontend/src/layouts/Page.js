import React, { useEffect } from "react"
import Container from "./Container"

function Page(props) {
  useEffect(() => {
    document.title = `${props.title} | Networks statistics`
  }, [])

  return <Container>{props.children}</Container>
}

export default Page
