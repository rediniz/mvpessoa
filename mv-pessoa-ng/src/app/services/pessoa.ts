import {Telefone} from "./telefone";

export class Pessoa {
 
    id:number;
    nome: string;
    cpf: string;
    email: string;
    dataNascimento: Date;
    telefones: Telefone[];
}