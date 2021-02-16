import React from "react";
import Pit from "./pit/Pit";

const Pits = (props) => {
  return (
    <div>
      <div className="row">
        {props.pitsPerPlayer.map((pit, pitId) => {
          return (
            <Pit
              key={pitId}
              pit={pit}
              clicked={props.onClickHandler}
              playerNumber={props.playerNumber}
            />
          );
        })}
      </div>
    </div>
  );
};

export default Pits;
