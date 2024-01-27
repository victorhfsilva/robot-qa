import { BrowserRouter, Route, Routes } from "react-router-dom"
import Home from "../pages/home/Home";
import Robots from "../pages/robots/Robots";
import Chat from "../pages/chat/Chat";
import About from "../pages/about/About";
import Header from "../components/header/Header";

function PageRouter() {
    return(
        <BrowserRouter>
        <Header />
        <Routes>
            <Route path="/" element={<Home />}/>
            <Route path="robots" element={<Robots />}/>
            <Route path="chat" element={<Chat />}/>
            <Route path="about" element={<About />}/>
        </Routes>
        </BrowserRouter>
    )
}

export default PageRouter;