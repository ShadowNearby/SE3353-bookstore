import { BookChart } from "../../compoents/admin/BookChart";
import {
  BarElement,
  CategoryScale,
  Chart,
  Legend,
  LinearScale,
  LineElement,
  PointElement,
  Title,
  Tooltip,
} from "chart.js";
import { UsersChart } from "../../compoents/admin/UsersChart";

Chart.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  BarElement,
  Title,
  Tooltip,
  Legend
);
export const AdminChartView = () => {
  return (
    <>
      <BookChart />
      <UsersChart />
    </>
  );
};
