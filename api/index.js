/*criando server*/
const app = require('express')();
const http = require('http').createServer(app);
const config = require('./config')
const bodyParser = require("body-parser");

app.use(bodyParser.urlencoded({
    extended: true
}));

app.use(bodyParser.json());

let alunos = [
{"id":1, "nome": "Randerson", "data_nascimento": 19990109, "matricula": 20858300, 
	"cpf": "477.147.259.74", "notas": [10, 8.2, 7.5]}, {"id":2, "nome": "Micael", "data_nascimento":19990531, "matricula": 20144381, 
	"cpf": "400.666.666.66", "notas": [10, 8.2, 7.5]}];


let qtdeAlunos = alunos.length;	
//page inicial da api
app.get("/", function(req, res) {
	const help = `
		<pre>
			Bem vindo a API de Randerson Mendes
            Esta api contém as seguintes endpoints:
            GET /alunos = retorna todos os alunos
            GET /alunos/:id = retorna alunos
            GET alunos/delete/:id = exclui aluno por id
            
		</pre>
	`
	
	res.send(help);
});

//retorna todos os dados
app.get("/alunos", (req, res) => {
	res.send(alunos)
})

//retorna aluno especifico
app.get("/alunos/:id", (req, res) => {
	const aluno = alunos.filter(a => a.id == req.params.id);
	
	if(aluno.length > 0) {
		res.send(aluno[0]);
	} else {
		res.send({});
	}
});


//deleta um aluno pelo id
app.get("/alunos/delete/:id", (req, res) => {
    alunos = alunos.filter(a => a.id != req.params.id);
    
    res.send({"sucesso":true, "msg": "Excluído com sucesso"});
});

//cadastra um novo aluno
app.post("/alunos/novo", (req, res) => {
   qtdeAlunos++;
   const nome = req.body.nome;
   const data_nascimento = req.body.data_nascimento;
   const matricula = req.body.matricula;
   const cpf = req.body.cpf;
   const id = qtdeAlunos;
    
   const novoAluno= {
       id, nome, data_nascimento, matricula, cpf
   }
   
   alunos.push(novoAluno);
    
   res.send({"sucesso": true, "msg": "Cadastrado com sucesso"})

});

//avalia um aluno atribuindo nota e o id
app.post("/alunos/avaliar", (req, res) => {
    const id = alunos.filter(a => a.id == req.params.id);
    
    const nota = req.body.nota;
    
    //const concatAluno = aluno + nota;
    
    alunos = id.concat(nota);
    
    res.send({"sucesso": true, "msg" : "Avaliado com sucesso"})
});

http.listen(config.port, function(){
    console.log(`Servidor rodando na porta ${config.port}`);
})

