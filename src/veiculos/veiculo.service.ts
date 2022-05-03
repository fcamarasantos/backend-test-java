import { HttpException, Injectable } from '@nestjs/common';
import { VEICULOS } from './veiculos.mock';

@Injectable()
export class VeiculoService {

  veiculos = VEICULOS;

  getVeiculos(): Promise<any> {
    return Promise.resolve(this.veiculos);
  }

  getVeiculo(veiculoId): Promise<any> {
    let id = Number(veiculoId.veiculoId)
    const veiculoFound = this.veiculos.find(veiculo => veiculo.id === id)

    if (!veiculoFound)
      throw new HttpException('Veículo não encontrado.', 404)
    return Promise.resolve(veiculoFound);
  }

  addVeiculo(newVeiculo): Promise<any> {
    this.veiculos.push(newVeiculo)
    return Promise.resolve(this.veiculos)
  }

  updateVeiculo(veiculo): Promise<any> {
    let id = Number(veiculo.id)
    let veiculoFound = this.veiculos.findIndex(veiculo => veiculo.id === id)
    if (veiculoFound === -1) {
      throw new HttpException('Veículo não encontrado', 404)
    }
    this.veiculos.splice(veiculoFound, 1, veiculo)
    return Promise.resolve(this.veiculos)
  }

  deleteVeiculo(veiculoId): Promise<any> {
    let id = Number(veiculoId.veiculoId)
    console.log(id)
    const veiculoFound = this.veiculos.findIndex(veiculo => veiculo.id === id)
    if (veiculoFound === -1) {
      throw new HttpException('Veículo não encontrado', 404)
    }
    this.veiculos.splice(veiculoFound, 1)
    return Promise.resolve(this.veiculos)
  }

}
