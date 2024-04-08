import * as React from 'react';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';

export default function MediaCard(props) {
  return (
    <Card sx={{ maxWidth: 400 }} style={{margin:10}}>
      <CardMedia
        sx={{ height: 200 }}
        image={"/uploadimg/"+props.info.bimglist[0]}
        title="green iguana"
      />
      <CardContent>
        <Typography gutterBottom variant="h5" component="div">
          {props.info.memail}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          {props.info.bcontent}
        </Typography>
      </CardContent>
      <CardActions>
      <button onClick={() => props.deleteChange(props.info.bno)}>Share</button>
      </CardActions>
    </Card>
  );
}