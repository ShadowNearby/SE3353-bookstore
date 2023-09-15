export async function getStatisticsPersonal(begin: Date, end: Date) {
  return await fetch("http://localhost:8080/api/user/statistics", {
    method: "POST",
    credentials: "include",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      beginDate: begin,
      endDate: end,
    }),
  })
    .then((res) => res.json())
    .catch((err) => console.log(err));
}
