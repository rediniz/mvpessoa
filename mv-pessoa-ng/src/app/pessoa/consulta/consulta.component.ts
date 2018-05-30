import { Component, OnInit } from '@angular/core';
 
import {Router} from '@angular/router';
 
import {PessoaService} from '../../services/pessoa.service';
 
import {Pessoa} from '../../services/pessoa';
 
@Component({
    selector: 'app-consulta-pessoa',
    templateUrl: './consulta.component.html',
    styleUrls:["./consulta.component.css"]
  })
  export class ConsultaComponent implements OnInit {
 
    private pessoas: Pessoa[] = new Array();
    private titulo:string;
 
    constructor(private pessoaService: PessoaService,
                private router: Router){}
 
    ngOnInit() {
 
      this.titulo = "Consulta de pessoas";
 
      this.pessoaService.getPessoas(null, null).subscribe(res => this.pessoas = res);
    }

    excluir(id:number, index:number):void {
 
      if(confirm("Deseja realmente excluir esse registro?")){
 
        this.pessoaService.excluirPessoa(id).subscribe(response => {
          this.pessoas.splice(index, 1);
          alert("Pessoa removida com sucesso.");
          },
          (erro) => {                    
            alert("Ocorreu um erro durante a requisição: "+JSON.parse(erro._body).message);
          });        
      }
 
    }
 
    editar(id:number):void{
 
      this.router.navigate(['/cadastro-pessoa',id]);
 
    }
  
    pesquisar(nome: string, cpf: string):void {
      this.pessoaService.getPessoas(nome, cpf).subscribe(res => this.pessoas = res);
    }
  
    getIdade(nascimento:string) {
      var parts     = nascimento.split('/');
      var dataISO   = new Date(parseInt(parts[2]), parseInt(parts[1]) - 1, parseInt(parts[0]));
      var diferenca = Date.now() - dataISO.getTime();
      var idade = Math.floor(diferenca / 1000 / 60 / 60 / 24 / 365);
      
      return idade;
    }
 
  }

