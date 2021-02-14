import React from "react";
import Pit from "./pit/Pit";

const Pits = (props) => {
  let titleForPlayer1 = <div>Pits for Player :1</div>;
  let titleForPlayer2 = <div>Pits for Player :2</div>;
  if (props.playerNumber == 1) {
    titleForPlayer2 = "";
  } else {
    titleForPlayer1 = "";
  }

  return (
    <div>
      {titleForPlayer2}
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
      {titleForPlayer1}
    </div>
  );
};

export default Pits;
