export async function DoMapReduce(): Promise<string> {
  const response = await fetch("http://localhost:8080/api/book/mr", {
    method: "POST",
    credentials: "include",
  });
  return response.text();
}
