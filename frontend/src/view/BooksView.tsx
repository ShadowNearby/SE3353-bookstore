import { Breadcrumb } from "antd";
import { BookList } from "../compoents/book/BookList";
import React from "react";
import { BookAuthorSearch } from "../compoents/book/BookAuthorSearch";
import { BookMapReduce } from "../compoents/book/BookMapReduce";

export const BooksView = (): JSX.Element => {
  // const [drawerOpen, setDrawerOpen] = useState(false);
  return (
    <>
      <Breadcrumb style={{ margin: "16px 0", fontSize: 20, paddingTop: 8 }}>
        <Breadcrumb.Item>{"图书"}</Breadcrumb.Item>
      </Breadcrumb>
      <BookAuthorSearch />
      <BookMapReduce />
      <div style={{ padding: 24, minHeight: 380, marginTop: 10 }}>
        <BookList />
      </div>
      {/*<Drawer open={drawerOpen} onClose={() => setDrawerOpen(false)}></Drawer>*/}
    </>
  );
};
