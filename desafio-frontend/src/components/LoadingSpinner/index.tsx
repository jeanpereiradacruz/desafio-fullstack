import React from "react";
import CircularProgress from "@mui/material/CircularProgress";

interface LoadingSpinnerProps {
  isLoading: boolean;
}

const LoadingSpinner: React.FC<LoadingSpinnerProps> = ({ isLoading }) => {
  if (!isLoading) return null;

  return (
    <div
      style={{
        position: "fixed",
        top: "50%",
        left: "50%",
        transform: "translate(-50%, -50%)",
        zIndex: "999"
      }}
    >
      <CircularProgress />
    </div>
  );
};

export default LoadingSpinner;
