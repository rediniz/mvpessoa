import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { MenuComponent } from './menu/menu.component';
import { CadastroComponent } from './pessoa/cadastro/cadastro.component';
import { ConsultaComponent } from './pessoa/consulta/consulta.component';

import { routing } from './../app.routes';

import { ConfigService } from './services/config.service';
import { PessoaService } from './services/pessoa.service';
import { Pessoa } from './services/pessoa';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    MenuComponent,
    CadastroComponent,
    ConsultaComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    routing
  ],
  providers: [ConfigService, PessoaService],
  bootstrap: [AppComponent]
})
export class AppModule { }
