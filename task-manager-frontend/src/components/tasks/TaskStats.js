import React, { useContext, useEffect, useState } from "react";
import { Modal } from "@material-ui/core";
import TopNavBarTask from "../NavigationBar/TopNavBarTask";
import "../../css/Task.css";
import { Context as TaskContext } from "../../context/store/TaskStore";
import apiServer from "../../config/apiServer";
import TaskSection from "./TaskSection";
import moment from "moment";
import TaskForm from "../Forms/AddTaskForm";
import TaskStat from "./TaskStat"

const TaskStats = () => {
  const [taskState, taskdispatch] = useContext(TaskContext);
  const [loading, setLoading] = useState(true);
  const [open, setOpen] = useState(false);

  const getUserTasks = async () => {
    const id = localStorage.getItem("userId");
    const res = await apiServer.get(`/task/findByAssigneeId/${id}`);
    await taskdispatch({ type: "get_user_tasks", payload: res.data });
    // setTasks(res.data);
    setLoading(false);
  };
  const openModal = () => {
    setOpen(true);
  };

  const closeModal = () => {
    setOpen(false);
  };

  useEffect(() => {
    getUserTasks();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  if (loading) {
    return <div>Loading...</div>;
  }


  const allTask = taskState.tasks;

  const openTask = taskState.tasks.filter((task) => task.completed === false);

  const completedTask = taskState.tasks.filter((task) => task.completed === true);

  const pastDueTask = taskState.tasks.filter((task) => {
    const date = new Date(task.due_date);
    const dueDate = moment(date);
    const currDate = moment(new Date());
    return dueDate.isBefore(currDate); //due date is before current date
  });

  return (
    <>
      <TopNavBarTask />
      <div className="tasks-container">
          <TaskStat title={"ALL"} stats={allTask.length} fontColor={"gray"} />
          <TaskStat title={"Open"} stats={openTask.length} fontColor={"blue"} />
          <TaskStat title={"Completed"} stats={completedTask.length} fontColor={"green"} />
          <TaskStat title={"Past Due"} stats={pastDueTask.length} fontColor={"red"} />
      </div>
    </>
  );
};

export default TaskStats;
