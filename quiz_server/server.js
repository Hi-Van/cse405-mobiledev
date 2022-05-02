

// Started from: ...

// server.js
const express = require('express');
// Define Express App
const app = express();
const PORT = process.env.PORT || 8080;
// Serve Static Assets
app.use(express.static('images'));
//app.use(express.static('questions'));
// Virtual Path Prefix '/static'
app.use('/static', express.static('images'))
//app.use('/static',  express.static('questions'));
app.use('/questions', (req, res) => {
    res.header("Content-Type",'application/json');
    // original link: c:\\users\\pgb15001\\t\\questionServe\\questions\\q_list2.json
    res.sendFile("C:\\Users\\vansi\\AndroidStudioProjects\\quiz_server\\questions\\q_list2.json");
  });
app.get('/test', (req, res) => res.json({ "answer": 42 }));

app.listen(PORT, () => {
    console.log('Server connected at:', PORT);
});
