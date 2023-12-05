import { getRequest, requestUrl } from "../../utility/request";
import { IBook } from "../../interface";

export async function getBookByName(name: string): Promise<IBook[]> {
  const url = `${requestUrl}api/book?name=${name}`;
  const response = await getRequest({ url: url });
  if (response.ok) return await response.json();
  console.error(response.body);
  throw Error;
}
