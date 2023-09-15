import { useEffect, useState } from "react";
import { IBook } from "../../interface";
import { getBooks } from "../../service/get/getBook";
import {
  Button,
  Drawer,
  Image,
  Input,
  InputNumber,
  message,
  Space,
  Table,
} from "antd";
import { addBook } from "../../service/admin/addBook";
import { putBook } from "../../service/admin/putBook";
import { deleteBook } from "../../service/admin/deleteBook";
import { UploadImage } from "../upload/UploadImage";

const { Column } = Table;
const { TextArea } = Input;
export const AdminBooks = () => {
  const [showedBooks, setShowedBooks] = useState<IBook[]>([]);
  const [books, setBooks] = useState<IBook[]>([]);
  useEffect(() => {
    getBooks().then((res: IBook[]) => {
      setBooks(res);
      setShowedBooks(res);
    });
  }, []);
  const [drawerOpen, setDrawerOpen] = useState(false);
  const [isAddBook, setIsAddBook] = useState(false);
  const [selectBookId, setSelectBookId] = useState(0);
  const [selectBookDesc, setSelectBookDesc] = useState("");
  const [selectBookName, setSelectBookName] = useState("");
  const [selectBookPrice, setSelectBookPrice] = useState(0);
  const [selectBookInv, setSelectBookInv] = useState(0);
  const [selectBookISBN, setSelectBookISBN] = useState("");
  const [selectBookAuthor, setSelectBookAuthor] = useState("");
  const [selectBookImage, setSelectBookImage] = useState("");
  const clearSelect = () => {
    setSelectBookName("");
    setSelectBookId(0);
    setSelectBookInv(0);
    setSelectBookISBN("");
    setSelectBookDesc("");
    setSelectBookPrice(0);
    setSelectBookAuthor("");
    setSelectBookImage("");
  };
  const setSelectBook = (value: IBook) => {
    setSelectBookName(value.name);
    setSelectBookId(value.id);
    setSelectBookInv(value.inventory);
    setSelectBookISBN(value.isbn);
    setSelectBookDesc(value.description);
    setSelectBookPrice(value.price);
    setSelectBookAuthor(value.author);
    setSelectBookImage(value.image);
  };
  const handlePutBook = async () => {
    setAddButtonLoading(true);
    await putBook({
      id: selectBookId,
      name: selectBookName,
      description: selectBookDesc,
      author: selectBookAuthor,
      image: selectBookImage,
      price: selectBookPrice,
      inventory: selectBookInv,
      isbn: selectBookISBN,
    });
    const newBooks = await getBooks();
    setBooks(newBooks);
    setShowedBooks(newBooks);
    await message.success("成功", 1);
    setAddButtonLoading(false);
    setDrawerOpen(false);
  };
  const handleDeleteBook = () => {
    setDeleteButtonLoading(true);
    deleteBook({ id: selectBookId }).then(() => {
      getBooks().then((res: IBook[]) => {
        setBooks(res);
        message
          .success("删除成功", 1)
          .then(() => setDeleteButtonLoading(false))
          .then(() => setDrawerOpen(false))
          .then(() => window.location.reload());
      });
    });
  };
  const handleAddBook = () => {
    setAddButtonLoading(true);
    addBook({
      name: selectBookName,
      description: selectBookDesc,
      author: selectBookAuthor,
      image: selectBookImage,
      price: selectBookPrice,
      inventory: selectBookInv,
      isbn: selectBookISBN,
    }).then(() =>
      getBooks().then((res: IBook[]) => {
        setBooks(res);
        setShowedBooks(res);
        message
          .success("添加成功", 1)
          .then(() => setAddButtonLoading(false))
          .then(() => clearSelect());
      })
    );
  };
  const handleSearchBook = (target: string) => {
    let newShowedBooks: IBook[] = [];
    books.forEach((value) => {
      if (value.name.match(target)) newShowedBooks.push(value);
    });
    setShowedBooks(newShowedBooks);
  };
  const handleRowClick = (value: IBook) => ({
    onClick: () => {
      setSelectBook(value);
      setDrawerOpen(true);
      setIsAddBook(false);
    },
  });

  const [addButtonLoading, setAddButtonLoading] = useState(false);
  const [deleteButtonLoading, setDeleteButtonLoading] = useState(false);
  return (
    <div>
      <Drawer onClose={() => setDrawerOpen(false)} open={drawerOpen}>
        <Space direction={"vertical"}>
          <span>{"书名"}</span>
          <Input
            value={selectBookName}
            onChange={(e) => {
              setSelectBookName(e.target.value);
            }}
          ></Input>
          <span>{"作者"}</span>
          <Input
            value={selectBookAuthor}
            onChange={(e) => {
              setSelectBookAuthor(e.target.value);
            }}
          ></Input>
          <span>{"价格"}</span>
          <InputNumber
            value={selectBookPrice}
            onChange={(value) => value && setSelectBookPrice(value)}
          ></InputNumber>
          <span>{"剩余库存"}</span>
          <InputNumber
            value={selectBookInv}
            onChange={(value) => value && setSelectBookInv(value)}
          ></InputNumber>
          <span>{"图片"}</span>
          <UploadImage state={selectBookImage} setState={setSelectBookImage} />
          <Input
            value={selectBookImage}
            onChange={(e) => setSelectBookImage(e.target.value)}
          ></Input>
          <span>{"ISBN"}</span>
          <Input
            value={selectBookISBN}
            onChange={(e) => setSelectBookISBN(e.target.value)}
          ></Input>
          <span>{"书籍简介"}</span>
          <TextArea
            rows={6}
            value={selectBookDesc}
            onChange={(e) => setSelectBookDesc(e.target.value)}
          ></TextArea>
          {!isAddBook ? (
            <>
              <Button
                type={"primary"}
                loading={addButtonLoading}
                onClick={handlePutBook}
              >
                {"确认更改"}
              </Button>
              <Button
                danger={true}
                loading={deleteButtonLoading}
                type={"primary"}
                onClick={handleDeleteBook}
              >
                {"删除"}
              </Button>
            </>
          ) : (
            <Button loading={addButtonLoading} onClick={handleAddBook}>
              {"确认添加"}
            </Button>
          )}
        </Space>
      </Drawer>
      <Space direction={"horizontal"} style={{ marginBottom: 20 }}>
        <Input.Search
          placeholder={"按书名搜索"}
          style={{ width: 400, marginBottom: 15, marginRight: 10 }}
          onSearch={handleSearchBook}
        ></Input.Search>
        <Button
          type={"primary"}
          onClick={() => {
            setIsAddBook(true);
            setDrawerOpen(true);
            clearSelect();
          }}
          style={{ marginBottom: 15 }}
        >
          {"添加书籍"}
        </Button>
      </Space>
      <Table dataSource={showedBooks} onRow={handleRowClick}>
        <Column
          title={"书名"}
          render={(value: IBook) => {
            return (
              <>
                <Image
                  src={value.image}
                  style={{ height: 100 }}
                  preview={false}
                ></Image>
                <span style={{ marginLeft: 5 }}>{value.name}</span>
              </>
            );
          }}
        ></Column>
        <Column
          title={"作者"}
          render={(value: IBook) => {
            return (
              <>
                <span style={{ marginLeft: 5 }}>{value.author}</span>
              </>
            );
          }}
        ></Column>
        <Column
          title={"单价"}
          render={(value: IBook) => {
            return <p>{value.price}</p>;
          }}
        ></Column>
        <Column
          title={"库存"}
          render={(value: IBook) => {
            return <p>{value.inventory}</p>;
          }}
        ></Column>
        <Column
          title={"ISBN"}
          render={(value: IBook) => {
            return <p>{value.isbn}</p>;
          }}
        ></Column>
      </Table>
    </div>
  );
};
