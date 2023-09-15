export async function getStatisticsBookByDate(begin: Date, end: Date) {
  return await fetch("http://localhost:8080/admin/book/statistics", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      beginDate: begin,
      endDate: end,
    }),
    credentials: "include",
  })
    .then((res) => res.json())
    .catch((err) => alert(err));
}
