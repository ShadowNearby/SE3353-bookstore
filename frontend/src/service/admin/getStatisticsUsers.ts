export async function getStatisticsUsersByDate(begin: Date, end: Date) {
  return await fetch("http://localhost:8080/admin/user/statistics", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      beginDate: begin,
      endDate: end,
    }),
    credentials: "include",
  })
    .then((res) => res.json())
    .catch((err) => console.log(err));
}
