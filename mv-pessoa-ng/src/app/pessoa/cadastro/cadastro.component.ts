import { Component, OnInit } from '@angular/core';
 
import {Router} from '@angular/router';
import { ActivatedRoute } from '@angular/router';
 
import {PessoaService} from '../../services/pessoa.service';
 
import { Pessoa } from '../../services/pessoa';
import { Telefone} from '../../services/telefone';
 
import { Observable } from 'rxjs/Observable';
 
@Component({
    selector: 'app-cadastro-pessoa',
    templateUrl: './cadastro.component.html',
    styleUrls:['./cadastro.component.css']
  })
  export class CadastroComponent implements OnInit {

    private titulo: string;
    private pessoa: Pessoa = new Pessoa();
    private telefones: Telefone[];

    constructor(private pessoaService: PessoaService,
                private router: Router,
                private activatedRoute: ActivatedRoute){}

    ngOnInit() {
 
      this.activatedRoute.params.subscribe(parametro=>{
 
        if(parametro["id"] == undefined){
 
          this.titulo = "Cadastrar pessoa";
          this.telefones = this.pessoa.telefones;
        }
        else{
 
          this.titulo = "Editar pessoa";
          this.pessoaService.getPessoa(Number(parametro["id"])).subscribe(res => this.pessoa = res);
        }
 
 
      });      
    }
  
  adicionarTelefone(ddd: string, numero: string) {
    if (this.pessoa.telefones == null) {
      this.pessoa.telefones = [];
      }
      this.pessoa.telefones.push({ id: null, ddd: ddd, numero: numero });
    }
  
    excluirTelefone(index: number) {
      if(confirm("Deseja excluir o telefone?")){
  
        this.pessoa.telefones.splice(index, 1);
        console.log(this.pessoa.telefones);
                
      }
    }
 
    salvar():void {
 
      if(this.pessoa.id == undefined){
 
        this.pessoaService.addPessoa(this.pessoa).subscribe(response => {

           let res:Response = <Response>response;

           alert("Dados salvos com sucesso.");
          this.pessoa = new Pessoa();
         },
         (erro) => {   
          alert("Ocorreu um erro durante a requisição: "+JSON.parse(erro._body).message);
         });
 
      }
      else{
 
        this.pessoaService.atualizarPessoa(this.pessoa).subscribe(response => {
 
          let res: Response = <Response>response;
          alert("Dados editados com sucesso.");
          this.router.navigate(['/consulta-pessoa']);
       },
       (erro) => {                    
         alert("Ocorreu um erro durante a requisição: "+JSON.parse(erro._body).message);
       });
      }
 
    }
 
  }