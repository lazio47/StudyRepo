import React, { useEffect, useState } from 'react';
import { Avatar, Typography, List, ListItem, ListItemText, Divider, Grid } from '@mui/material';
import "./NotFound"
import NotFound from './NotFound';

const ProfilePage = () => {
    const valor = JSON.parse(localStorage.getItem('lusoAirlinesCurrentUser'));
    const [cliente, setCliente] = useState({
        nome: 'N/A',
        email: 'N/A',
        numeroVoo: 'N/A',
        voosAnteriores: ["N/A"],
        imagem: 'N/A'
    });

    useEffect(() => {
        if (valor && cliente.nome === 'N/A') {
            console.log("Aqui->" + valor.userFirstName);
            setCliente({
                nome: valor["userFirstName"] + " " + valor["userLastName"],
                email: valor["userEmail"],
                numeroVoo: '123456',
                voosAnteriores: ['Lisboa', 'Madri', 'Paris'],
                imagem: 'joao.png'
            });
        }
    }, [valor, cliente]); // Adicionando cliente ao array de dependências

    if (cliente.email === 'N/A' && cliente.imagem === 'N/A') {
        return <NotFound />;
    }else{
    return (
        <Grid container spacing={3} alignItems="center">
            <Grid item>
                <Avatar alt={cliente.nome} src={cliente.imagem} sx={{ width: 500, height: 500 }} />
            </Grid>
            <Grid item xs>
                <Typography variant="h4">Perfil do Cliente</Typography>
                <Typography><strong>Nome:</strong> {cliente.nome}</Typography>
                <Typography><strong>Email:</strong> {cliente.email}</Typography>
                <Typography><strong>Número do Voo:</strong> {cliente.numeroVoo}</Typography>
                <Typography variant="h4">Voos Anteriores</Typography>
                <List>
                    {cliente.voosAnteriores.map((voo, index) => (
                        <div key={index}>
                            <ListItem>
                                <ListItemText primary={voo} />
                            </ListItem>
                            {index < cliente.voosAnteriores.length - 1 && <Divider />}
                        </div>
                    ))}
                </List>
            </Grid>
        </Grid>
    );
    }
};

export default ProfilePage;
