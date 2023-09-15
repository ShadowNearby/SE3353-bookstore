import { IBook } from "../interface";
import { Breadcrumb } from "antd";
import React, { useEffect, useState } from "react";
import { Link, useLocation } from "react-router-dom";
import { BookDetail } from "../compoents/book/BookDetail";
import { getBookById } from "../service/get/getBook";

interface LocationState {
  id: number;
}

export const BookDetailsView = (): JSX.Element => {
  const state: LocationState = useLocation().state;
  const [book, setBook] = useState<IBook>();
  useEffect(() => {
    getBookById(state.id).then((res: IBook) => setBook(res));
  }, []);
  return (
    <>
      <Breadcrumb style={{ margin: "16px 0", paddingTop: 8, fontSize: 20 }}>
        <Breadcrumb.Item>
          <Link to={"/books"}>{"图书"}</Link>
        </Breadcrumb.Item>
        {book && <Breadcrumb.Item>{book.name}</Breadcrumb.Item>}
      </Breadcrumb>
      {book && <BookDetail book={book} />}
    </>
  );
};
