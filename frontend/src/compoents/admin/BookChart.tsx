import { useEffect, useState } from "react";
import { Bar } from "react-chartjs-2";
import { DatePicker, Select, Space, Tabs } from "antd";
import { backgroundColor, boarderColor } from "../../utility/color";
import dayjs from "dayjs";
import { getStatisticsBookByDate } from "../../service/admin/getStatisticsBooks";
import { MonthOfMs, WeekOfMs, YearOfMs } from "../../utility/timeConst";

interface IBookForm {
  bookName: string;
  count: number;
}

interface ITabItem {
  key: string;
  label: string;
}

const selectOptions = [
  { value: 3, label: "3" },
  { value: 5, label: "5" },
  { value: 10, label: "10" },
  { value: 20, label: "20" },
  { value: 50, label: "50" },
];
const options = {
  indexAxis: "y" as const,
  elements: {
    bar: {
      borderWidth: 2,
    },
  },
  responsive: true,
  plugins: {
    legend: {
      display: false,
    },
    title: {
      display: true,
      text: "图书销量排行榜",
    },
  },
};
const tabItems: ITabItem[] = [
  {
    key: "1",
    label: "过去7天",
  },
  {
    key: "2",
    label: "过去30天",
  },
  {
    key: "3",
    label: "过去365天",
  },
  {
    key: "4",
    label: "自定义",
  },
];
export const BookChart = () => {
  const [beginDate, setBeginDate] = useState<Date>(
    new Date(new Date().getTime() - WeekOfMs)
  );
  const [endDate, setEndDate] = useState<Date>(new Date());
  const [maxBook, setMaxBook] = useState(3);
  const [books, setBooks] = useState<IBookForm[]>([]);
  const [bookLabel, setBookLabel] = useState<string[]>([]);
  const [bookData, setBookData] = useState<number[]>([]);
  const handleGetBooks = (begin: Date, end: Date) => {
    getStatisticsBookByDate(begin, end).then((res: IBookForm[]) => {
      setBooks(res);
      bookStatistic(res, maxBook);
    });
  };
  useEffect(() => {
    handleGetBooks(beginDate, endDate);
  }, []);

  const bookStatistic = (books: IBookForm[], maxBook: number) => {
    const length = books.length > maxBook ? maxBook : books.length;
    let bookLabelList: string[] = [];
    let bookDataList: number[] = [];
    for (let i = 0; i < length; i++) {
      bookDataList.push(books[i].count);
      bookLabelList.push(books[i].bookName);
    }
    setBookLabel(bookLabelList);
    setBookData(bookDataList);
  };
  const [chosenTab, setChosenTab] = useState("1");
  const handleTabChange = (value: string) => {
    setChosenTab(value);
    let begin = new Date(),
      end = new Date();
    switch (value) {
      case "1":
        begin = new Date(new Date().getTime() - WeekOfMs);
        break;
      case "2":
        begin = new Date(new Date().getTime() - MonthOfMs);
        break;
      case "3":
        begin = new Date(new Date().getTime() - YearOfMs);
        break;
      case "4":
        break;
    }
    setBeginDate(begin);
    setEndDate(end);
    handleGetBooks(begin, end);
  };
  return (
    <>
      <div style={{ width: "60%", position: "relative" }}>
        <Tabs
          items={tabItems}
          activeKey={chosenTab}
          onChange={handleTabChange}
        ></Tabs>
        <Space direction={"horizontal"}>
          <Select
            options={selectOptions}
            style={{ width: 100 }}
            value={maxBook}
            onSelect={(value) => {
              setMaxBook(value);
              bookStatistic(books, value);
            }}
          />
          <DatePicker.RangePicker
            allowClear={false}
            value={[dayjs(beginDate), dayjs(endDate)]}
            onChange={(value) => {
              setChosenTab("4");
              if (value == null) return;
              const [selectBegin, selectEnd] = value;
              const begin = selectBegin ? selectBegin.toDate() : beginDate;
              const end = selectEnd ? selectEnd.toDate() : endDate;
              setBeginDate(begin);
              setEndDate(end);
              handleGetBooks(begin, end);
            }}
          />
        </Space>
        <Bar
          options={options}
          data={{
            labels: bookLabel,
            datasets: [
              {
                label: "销量",
                data: bookData,
                borderColor: boarderColor,
                backgroundColor: backgroundColor,
              },
            ],
          }}
        />
      </div>
    </>
  );
};
