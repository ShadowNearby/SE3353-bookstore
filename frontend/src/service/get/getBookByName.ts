import {postRequest, requestUrl} from "../../utility/request";
import {IBook} from "../../interface"; // export async function getBookByName(name: string): Promise<IBook[]> {

// export async function getBookByName(name: string): Promise<IBook[]> {
//   const url = `${requestUrl}api/book?name=${name}`;
//   const response = await getRequest({ url: url });
//   if (response.ok) return await response.json();
//   console.error(response.body);
//   throw Error;
// }
export async function getBookByName(name: string): Promise<IBook[]> {
  const url = `${requestUrl}graphql`;
  const body = {
    query: `query bookDetails($name:String) {
    bookByName(name: $name) {
        id
        name
        author
        image
        price
        author
        description
        inventory
        isbn
    }
}`,
    variables: { name: name },
  };
  const response = await postRequest({ url: url, body: body });
  if (response.ok) {
    const json = await response.json();
    return json.data.bookByName;
  }
  console.error(response.body);
  throw Error;
}
