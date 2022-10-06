import React, { useState } from "react";
import Routes from "./components/Routes";
import AuthContext from "./context/AuthContext";
import UserStore from "./context/store/UserStore";
import TeamStore from "./context/store/TeamStore";
import TaskStore from "./context/store/TaskStore";
import ProjectStore from "./context/store/ProjectStore";
import TasklistStore from "./context/store/TasklistStore";
import "./css/Home.css";
import ReminderCalendar from "react-reminder-calendar/dist"

const App = () => {
  const [auth, setAuth] = useState(localStorage.getItem("token") || "");
  const [userId, setUserId] = useState(localStorage.getItem("userId") || null);
  const [email, setEmail] = useState(localStorage.getItem("email") || null);
  const [user, setUser] = useState(localStorage.getItem("user") || null);

  const [sidebar, setSidebar] = useState(true);
  const showSidebar = () => setSidebar(!sidebar);
  const logout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("email");
    localStorage.removeItem("userId");
    setAuth(null);
    setEmail(null);
    setUserId(null);
  };
  const context = {
    auth,
    setAuth,
    userId,
    setUserId,
    email,
    setEmail,
    user,
    setUser,
    sidebar,
    setSidebar,
    showSidebar,
    logout,
  };

  const data = [
    {
        title: "TODAY, NOV 4",
        items: [
            {
                title: "Dinner with Richard",
                subTitle: "Richards House",
                icon: "fa fa-map-pin",
                startTime: "20:00",
                endTime: "21:00",
                isAllDay: false,
                allDayTitle: "All Day",
                separatorColor: "#26bdc6",
                style: {},
                infoViewComponent: null,
                rightViewComponent: null
            },
            {
                title: "Online meeting",
                subTitle: "Zoom",
                icon: "",
                startTime: "18:00",
                endTime: "19:30",
                isAllDay: false,
                allDayTitle: "All Day",
                separatorColor: "#a326c6",
                style: {},
                infoViewComponent: <div style={{fontSize: 12}}><small><b>Zoom Link</b>  <kbd>https://zoom.us/udyt4RE</kbd></small></div>,
                rightViewComponent: null
            }
        ],
        rightButton: {
            title: "+",
            show: true,
            props: {
                className: "",
                style: {backgroundColor: '#ccc', border: 'none', width: 22, height: 22}
            }
        }
    },
    {
        title: "TOMORROW, NOV 5",
        items: [
            {
                title: "Join the Summit",
                subTitle: "City Center",
                icon: "fa fa-map-pin",
                startTime: "10:00",
                endTime: "13:00",
                isAllDay: true,
                allDayTitle: "All Day",
                separatorColor: "#69c626",
                style: {},
                infoViewComponent: null,
                rightViewComponent: null
            },
            {
                title: "Enroll the online course now",
                subTitle: "Udemy",
                icon: "",
                startTime: "18:00",
                endTime: "19:30",
                isAllDay: false,
                allDayTitle: "All Day",
                separatorColor: "#e5245a",
                style: {},
                infoViewComponent: null,
                rightViewComponent: null
            }
        ],
        rightButton: {
            title: "+",
            show: true,
            props: {
                className: "",
                style: {backgroundColor: '#ccc', border: 'none', width: 22, height: 22}
            }
        }
    }
]

  const handleItemClick = () =>  (dateSection, item, index) => {
        
  }

  const handleRightButtonClick = () =>  (dateSection, index) => {
      
  }

  return (
    <AuthContext.Provider value={context}>
      <UserStore>
        <ProjectStore>
          <TeamStore>
            <TasklistStore>
              <TaskStore>
                <Routes />
              </TaskStore>
            </TasklistStore>
          </TeamStore>
        </ProjectStore>
      </UserStore>
      
    </AuthContext.Provider>
  );
};

export default App;
