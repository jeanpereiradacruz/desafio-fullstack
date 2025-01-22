import { Box, Button, Modal, TextField, Typography } from "@mui/material";
import { useContext, useEffect, useState } from "react";
import { FavoriteList } from "../../types/FavoriteList";
import { AuthContext } from "../../contexts/Auth/AuthContext";
import { FavoriteListRequest } from "../../types/FavoriteListRequest";
import CustomSnackbar from "../CustomSnackBar";

interface FavoriteListModalProps {
  isModalOpen: boolean;
  handleCloseModal: () => void;
}

export const FavoriteListModal: React.FC<FavoriteListModalProps> = ({
  isModalOpen,
  handleCloseModal
}) => {
  const auth = useContext(AuthContext);

  const [favoriteList, setFavoriteList] = useState<FavoriteList | null>(null);

  const [formData, setFormData] = useState({
    title: auth?.client?.favoriteList?.title || "",
    description: auth?.client?.favoriteList?.description || ""
  });

  const [openSnackbar, setOpenSnackbar] = useState(false);
  const [snackbarMessage, setSnackbarMessage] = useState("");
  const [severity, setSeverity] = useState<"success" | "error" | "warning">(
    "success"
  );

  const showSnackbar = (
    message: string,
    severity: "success" | "error" | "warning"
  ) => {
    setSnackbarMessage(message);
    setSeverity(severity);
    setOpenSnackbar(true);
  };

  const handleCloseSnackbar = () => {
    setOpenSnackbar(false);
  };

  useEffect(() => {
    if (isModalOpen) {
      const updatedFavoriteList = auth?.client?.favoriteList || null;
      setFavoriteList(updatedFavoriteList);

      if (updatedFavoriteList) {
        setFormData((prevFormData) => ({
          ...prevFormData,
          title: updatedFavoriteList.title || "",
          description: updatedFavoriteList.description || ""
        }));
      }
    }
  }, [isModalOpen, auth?.client?.favoriteList]);

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value
    }));
  };

  const saveList = async () => {
    let favoriteListRequest: FavoriteListRequest = {
      id: !favoriteList?.id ? "" : favoriteList.id,
      title: formData?.title,
      description: formData?.description,
      clientId: auth?.client?.id
    };

    await auth.addFavoriteList(favoriteListRequest);
    showSnackbar(`A lista foi ${favoriteListRequest.id === "" ? 'criada' : 'alterada'} com sucesso`, 'success');
    handleCloseModal();
  };

  const removeList = async () => {
    if (favoriteList?.id) {
      let clientId = auth?.client?.id;
      await auth.removeFavoriteList(clientId || "");
      setFavoriteList(null);
      setFormData({ title: "", description: "" });
      showSnackbar('A lista foi removida com sucesso!', 'success');
      handleCloseModal();
    } else {
      showSnackbar('Nenhuma lista pra excluir!', 'warning');
    }
  };

  return (
    <>
      <Modal
        open={isModalOpen}
        onClose={handleCloseModal}
        aria-labelledby="modal-title"
        aria-describedby="modal-description"
      >
        <Box
          sx={{
            position: "absolute" as const,
            top: "50%",
            left: "50%",
            transform: "translate(-50%, -50%)",
            width: 400,
            bgcolor: "background.paper",
            boxShadow: 24,
            p: 4,
            borderRadius: 2
          }}
        >
          <Typography id="modal-title" variant="h6" component="h2">
            {favoriteList
              ? `Alteração ${favoriteList?.title}`
              : "Criar Nova Lista"}
          </Typography>

          <Typography id="modal-description" sx={{ mt: 2 }}>
            {favoriteList
              ? "Edite as informações da sua lista favorita abaixo:"
              : "Crie uma nova lista favorita preenchendo as informações abaixo:"}
          </Typography>

          <TextField
            fullWidth
            label="Nome"
            name="title"
            variant="outlined"
            defaultValue={formData?.title}
            onChange={handleInputChange}
            sx={{ mt: 3 }}
          />

          <TextField
            fullWidth
            name="description"
            label="Descricao"
            variant="outlined"
            onChange={handleInputChange}
            defaultValue={formData?.description}
            sx={{ mt: 3 }}
          />

          <Button
            fullWidth
            variant="contained"
            color="success"
            onClick={saveList}
            sx={{ mt: 2 }}
          >
            Salvar
          </Button>

          {favoriteList && (
            <Button
              fullWidth
              variant="contained"
              color="error"
              onClick={removeList}
              sx={{ mt: 2 }}
            >
              Remover Lista
            </Button>
          )}
        </Box>
      </Modal>

      <CustomSnackbar
        open={openSnackbar}
        message={snackbarMessage}
        severity={severity}
        handleClose={handleCloseSnackbar}
      />
    </>
  );
};
