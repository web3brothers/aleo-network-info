import { Navbar, Nav, Container } from "react-bootstrap"
import { Link } from "react-router-dom"

function Header() {
  return (
    <Navbar className="container" bg="light" expand="lg">
      <Container fluid>
        <div className="d-flex justify-content-center align-items-center ml-2 ml-lg-0">
          <Navbar.Brand className="mr-2" as={Link} to="/">
            {"Aleo network info"}
          </Navbar.Brand>
        </div>
        <Navbar.Toggle aria-controls="basic-navbar-nav" className="mr-2">
          <span className="navbar-toggler-bar burger-lines"></span>
          <span className="navbar-toggler-bar burger-lines"></span>
          <span className="navbar-toggler-bar burger-lines"></span>
        </Navbar.Toggle>
        <Navbar.Collapse className="justify-content-center" id="basic-navbar-nav">
          <Nav className="nav mr-auto" navbar>
            <Nav.Item>
              <Nav.Link className="m-0" as={Link} to="/">
                <i className="nc-icon nc-zoom-split"></i>
                <span className="d-lg-block">Peers dashboard</span>
              </Nav.Link>
            </Nav.Item>
            <Nav.Item>
              <Nav.Link className="m-0" as={Link} to="/boot-nodes">
                <i className="nc-icon nc-zoom-split"></i>
                <span className="d-lg-block">Boot nodes</span>
              </Nav.Link>
            </Nav.Item>
            <Nav.Item>
              <Nav.Link className="m-0" as={Link} to="/miners">
                <i className="nc-icon nc-zoom-split"></i>
                <span className="d-lg-block">Miners</span>
              </Nav.Link>
            </Nav.Item>
            <Nav.Item>
              <Nav.Link className="m-0" as={Link} to="/full-nodes">
                <i className="nc-icon nc-zoom-split"></i>
                <span className="d-lg-block">Full nodes</span>
              </Nav.Link>
            </Nav.Item>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  )
}

export default Header
