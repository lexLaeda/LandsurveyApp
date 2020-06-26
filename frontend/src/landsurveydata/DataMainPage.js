import PointPage from "./point/PointPage";
import BaselinePage from "./baseline/BaselinePage";
import LevelReferencePage from "./levelreferenses/LevelReferencePage";
import React from "react";


export default function DataMainPage() {
        return(
            <div id="carouselExampleInterval" className="carousel slide" data-ride="carousel">
                <ol className="carousel-indicators">
                    <li data-target="#carouselExampleInterval" data-slide-to="0" className="active"></li>
                    <li data-target="#carouselExampleInterval" data-slide-to="1"></li>
                    <li data-target="#carouselExampleInterval" data-slide-to="2"></li>
                </ol>
                <div className="carousel-inner">
                    <div className="carousel-item active" data-interval="10000">
                        <PointPage/>
                    </div>
                    <div className="carousel-item" data-interval="2000">
                        <BaselinePage/>
                    </div>
                    <div className="carousel-item">
                        <LevelReferencePage/>
                    </div>
                </div>
                <a className="carousel-control-prev" href="#carouselExampleInterval" role="button"
                   data-slide="prev">
                    <span className="carousel-control-prev-icon" aria-hidden="false"></span>
                    <span className="sr-only">Previous</span>
                </a>
                <a className="carousel-control-next" href="#carouselExampleInterval" role="button"
                   data-slide="next">
                    <span className="carousel-control-next-icon" aria-hidden="false"></span>
                    <span className="sr-only">Next</span>
                </a>
            </div>
        )
}