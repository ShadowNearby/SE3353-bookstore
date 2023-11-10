export interface IBookForm {
  bookName: string;
  count: number;
}

export interface IResponse {
  books: IBookForm[];
  spend: number;
}

export async function getStatisticsPersonal(
  begin: Date,
  end: Date
): Promise<IResponse> {
  const response = await fetch("http://localhost:8080/api/user/statistics", {
    method: "POST",
    credentials: "include",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      beginDate: begin,
      endDate: end,
    }),
  });
  if (response.ok) {
    return response.json();
  }
  return { books: [], spend: 0 };
}
