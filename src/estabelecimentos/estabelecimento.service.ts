import { HttpException, Injectable } from '@nestjs/common';
import { ESTABELECIMENTOS } from './estabelecimentos.mock';

@Injectable()
export class EstabelecimentoService {

  estabelecimentos = ESTABELECIMENTOS;

  getEstabelecimentos(): Promise<any> {
    return Promise.resolve(this.estabelecimentos);
  }

  getEstabelecimento(estabelecimentoCnpj): Promise<any> {
    let cnpj = estabelecimentoCnpj.estabelecimentoCnpj
    const estabelecimentoFound = this.estabelecimentos.find(estabelecimento => estabelecimento.cnpj === cnpj)
    if (!estabelecimentoFound)
      throw new HttpException('Estabelecimento não encontrado.', 404)
    return Promise.resolve(estabelecimentoFound);
  }

  addEstabelecimento(newEstabelecimento): Promise<any> {
    this.estabelecimentos.push(newEstabelecimento)
    return Promise.resolve(this.estabelecimentos)
  }

  updateEstabelecimento(estabelecimento): Promise<any> {
    let cnpj = estabelecimento.cnpj
    console.log(estabelecimento)
    let estabelecimentoFound = this.estabelecimentos.findIndex(estabelecimento => estabelecimento.cnpj === cnpj)
    if (estabelecimentoFound === -1) {
      throw new HttpException('Estabelecimento não encontrado', 404)
    }
    this.estabelecimentos.splice(estabelecimentoFound, 1, estabelecimento)
    return Promise.resolve(this.estabelecimentos)
  }

  deleteEstabelecimento(estabelecimentoCnpj): Promise<any> {
    let cnpj = estabelecimentoCnpj.estabelecimentoCnpj
    console.log(cnpj)
    const estabelecimentoFound = this.estabelecimentos.findIndex(estabelecimento => estabelecimento.cnpj === cnpj)
    if (estabelecimentoFound === -1) {
      throw new HttpException('Estabelecimento não encontrado', 404)
    }
    this.estabelecimentos.splice(estabelecimentoFound, 1)
    return Promise.resolve(this.estabelecimentos)
  }

}
