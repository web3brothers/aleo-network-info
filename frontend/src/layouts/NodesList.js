import Page from "./Page"
import { useEffect, useState } from "react"
import Axios from "axios"
import ReactPaginate from "react-paginate"
import { Table } from "react-bootstrap"
import humanizeDuration from "humanize-duration"

function NodesList(props) {
  const [offset, setOffset] = useState(0)
  const [data, setData] = useState({ items: [], total: 0, page: 0 })
  const [limit] = useState(25)
  const [pageCount, setPageCount] = useState(0)

  useEffect(() => {
    async function fetchData() {
      try {
        let response = await Axios.get(`/api/v1/nodes?nodes_type=${props.nodesType}&offset=${offset}&limit=${limit}`)
        setData(response.data)
        setPageCount(Math.ceil(response.data.total / limit))
      } catch (e) {
        console.error(e)
      }
    }
    fetchData()
  }, [offset, pageCount, props.nodesType])

  function getCheckedMarkIcon() {
    return (
      <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="green" className="bi bi-check-lg" viewBox="0 0 16 16">
        <path d="M13.485 1.431a1.473 1.473 0 0 1 2.104 2.062l-7.84 9.801a1.473 1.473 0 0 1-2.12.04L.431 8.138a1.473 1.473 0 0 1 2.084-2.083l4.111 4.112 6.82-8.69a.486.486 0 0 1 .04-.045z" />
      </svg>
    )
  }

  function getCrossIcon() {
    return (
      <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="red" class="bi bi-x-lg" viewBox="0 0 16 16">
        <path d="M1.293 1.293a1 1 0 0 1 1.414 0L8 6.586l5.293-5.293a1 1 0 1 1 1.414 1.414L9.414 8l5.293 5.293a1 1 0 0 1-1.414 1.414L8 9.414l-5.293 5.293a1 1 0 0 1-1.414-1.414L6.586 8 1.293 2.707a1 1 0 0 1 0-1.414z" />
      </svg>
    )
  }

  function calculateUptime(launched) {
    if (launched !== null) {
      let startTime = Date.parse(launched)
      let endTime = Date.now()
      return humanizeDuration(endTime - startTime, { largest: 3 })
    }
    return ""
  }

  function handlePageClick(e) {
    let selectedPage = e.selected
    setOffset(parseInt(selectedPage, 10) * limit)
  }

  return (
    <Page className="text-center">
      <Table striped bordered hover size="sm">
        <thead>
          <tr>
            <th>Node's IP</th>
            <th>Synced</th>
            <th>Block count</th>
            <th>Uptime</th>
            <th>Version</th>
            <th>Country</th>
            <th>Hosting</th>
          </tr>
        </thead>
        <tbody>
          {data.items.map((item) => {
            return (
              <tr key={item.ip}>
                <td>{item.ip}</td>
                <td className="text-center">{item.syncing === true ? getCrossIcon() : getCheckedMarkIcon()}</td>
                <td>{item.blockCount}</td>
                <td>{calculateUptime(item.launched)}</td>
                <td>{item.version}</td>
                <td>{item.country}</td>
                <td>{item.org}</td>
              </tr>
            )
          })}
        </tbody>
      </Table>
      <ReactPaginate previousLabel={"prev"} nextLabel={"next"} breakLabel={"..."} pageLinkClassName={"page-link"} previousLinkClassName={"page-link"} previousClassName={"page-item"} breakClassName={"page-item"} breakLinkClassName={"page-link"} pageClassName={"page-item"} containerClassName={"pagination"} nextClassName={"page-item"} nextLinkClassName={"page-link"} activeClassName={"active"} pageCount={pageCount} marginPagesDisplayed={2} pageRangeDisplayed={3} onPageChange={handlePageClick} />
    </Page>
  )
}

export default NodesList
