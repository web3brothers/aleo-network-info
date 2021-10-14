import { Navbar, Nav, Container } from "react-bootstrap"
import { Link } from "react-router-dom"

function Header() {
  return (
    <Navbar className="container" bg="light" expand="lg">
      <Container fluid>
        <div className="d-flex justify-content-center align-items-center ml-2 ml-lg-0">
          <Navbar.Brand className="mr-2" href="https://aleo.org/" target="_blank">
            <svg width="100%" height="100%" viewBox="0 0 100 27" fill="none" className="sc-fzowVh sc-fzqzEs hSQVKb" style={{ height: "30px", width: "auto" }}>
              <path d="M11.5602 3.18115C12.5377 1.42425 14.7543 0.792379 16.5112 1.76983V1.76983C18.2681 2.74728 18.9 4.96391 17.9226 6.72081L8.13193 24.3188C7.15448 26.0757 4.93785 26.7076 3.18095 25.7301V25.7301C1.42405 24.7527 0.792179 22.536 1.76963 20.7791L11.5602 3.18115Z" fill="url(#paint0_linear)"></path>
              <path d="M18.0218 3.18115C17.0443 1.42425 14.8277 0.792379 13.0708 1.76983V1.76983C11.3139 2.74728 10.682 4.96391 11.6595 6.72081L21.4501 24.3188C22.4275 26.0757 24.6442 26.7076 26.4011 25.7301V25.7301C28.158 24.7527 28.7899 22.536 27.8124 20.7791L18.0218 3.18115Z" fill="url(#paint1_linear)"></path>
              <path d="M43.2963 4.48584H46.8973L53.1991 21.7564H49.1121L47.7755 17.8903H42.4015L41.0871 21.7564H37L43.2963 4.48584ZM47.1127 15.1398L45.0968 9.03132L43.0312 15.1343H47.1127V15.1398Z" fill="black"></path>
              <path d="M55.1714 4H59.0651V21.7566H55.1714V4Z" fill="black"></path>
              <path d="M67.7859 22.0001C66.7144 22.0001 65.7534 21.8234 64.9029 21.4754C64.0523 21.1275 63.3288 20.6525 62.7378 20.0505C62.1469 19.4485 61.694 18.7581 61.3902 17.9683C61.0809 17.184 60.9263 16.3611 60.9263 15.4995C60.9263 14.5771 61.0809 13.7045 61.3902 12.8871C61.6995 12.0697 62.1469 11.3517 62.7268 10.7331C63.3122 10.1145 64.0302 9.62847 64.8808 9.27499C65.7313 8.916 66.7034 8.73926 67.7859 8.73926C68.8739 8.73926 69.835 8.916 70.68 9.27499C71.525 9.63399 72.2375 10.1145 72.8229 10.722C73.4084 11.3296 73.8502 12.031 74.1485 12.8263C74.4467 13.6216 74.5958 14.4556 74.5958 15.3338C74.5958 15.5602 74.5848 15.7812 74.5737 16.0021C74.5572 16.223 74.5351 16.4108 74.5019 16.571H65.063C65.1293 17.4326 65.4386 18.0843 66.002 18.5261C66.5598 18.9735 67.1949 19.1944 67.9129 19.1944C68.4818 19.1944 69.0286 19.0563 69.5533 18.7802C70.078 18.504 70.4315 18.134 70.6137 17.659L74.386 17.6535C74.049 19.2828 72.6407 20.4095 71.5858 21.0446C70.5253 21.6798 69.2606 22.0001 67.7859 22.0001ZM70.4867 14.1684C70.4038 13.3565 70.1111 12.7159 69.6085 12.2464C69.1059 11.7769 68.4818 11.5394 67.7362 11.5394C66.974 11.5394 66.3444 11.7769 65.8528 12.2574C65.3558 12.738 65.0686 13.3731 64.9912 14.1684H70.4867Z" fill="black"></path>
              <path d="M83.3657 21.9997C82.2777 21.9997 81.3111 21.823 80.4606 21.464C79.61 21.105 78.892 20.6245 78.3066 20.0169C77.7211 19.4094 77.2793 18.7025 76.9811 17.9016C76.6828 17.1008 76.5337 16.2613 76.5337 15.3831C76.5337 14.5049 76.6828 13.671 76.9811 12.8646C77.2793 12.0637 77.7211 11.3568 78.3066 10.7493C78.892 10.1417 79.61 9.65569 80.4606 9.29117C81.3111 8.92665 82.2832 8.74438 83.3657 8.74438C84.4538 8.74438 85.4148 8.92665 86.2598 9.29117C87.1048 9.65569 87.8173 10.1417 88.4027 10.7493C88.9882 11.3568 89.43 12.0637 89.7393 12.8646C90.0486 13.6654 90.2033 14.5049 90.2033 15.3831C90.2033 16.2613 90.0541 17.0952 89.7559 17.9016C89.4576 18.7025 89.0158 19.4094 88.4304 20.0169C87.8449 20.6245 87.1269 21.105 86.2764 21.464C85.4203 21.8175 84.4538 21.9997 83.3657 21.9997ZM80.5213 15.3831C80.5213 16.3883 80.7864 17.1947 81.3222 17.8022C81.8579 18.4097 82.5373 18.7135 83.3657 18.7135C83.7689 18.7135 84.1445 18.6307 84.4869 18.4705C84.8293 18.3103 85.122 18.0839 85.3761 17.7912C85.6246 17.4984 85.8235 17.145 85.9726 16.7307C86.1162 16.3165 86.1935 15.8691 86.1935 15.3831C86.1935 14.3779 85.9284 13.5715 85.3927 12.964C84.8569 12.3565 84.1831 12.0527 83.3768 12.0527C82.9736 12.0527 82.5925 12.1355 82.2445 12.2957C81.8966 12.4559 81.5983 12.6823 81.3443 12.975C81.0902 13.2678 80.8969 13.6212 80.7478 14.0355C80.5931 14.4442 80.5213 14.8971 80.5213 15.3831Z" fill="black"></path>
              <defs>
                <linearGradient id="paint0_linear" x1="17.1514" y1="1.57353" x2="3.18095" y2="25.7301" gradientUnits="userSpaceOnUse">
                  <stop stopColor="#2BD6EF"></stop>
                  <stop offset="1" stopColor="#0B39F5"></stop>
                </linearGradient>
                <linearGradient id="paint1_linear" x1="8.33944" y1="-5.97941" x2="30.081" y2="30.6306" gradientUnits="userSpaceOnUse">
                  <stop stopColor="#01B3FF"></stop>
                  <stop offset="1" stopColor="#4FF3E2"></stop>
                </linearGradient>
              </defs>
            </svg>
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
            <Nav.Item>
              <Nav.Link className="m-0" href="https://www.aleo.network/" target="_blank">
                <i className="nc-icon nc-zoom-split"></i>
                <span className="d-lg-block">Aleo Explorer</span>
              </Nav.Link>
            </Nav.Item>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  )
}

export default Header
