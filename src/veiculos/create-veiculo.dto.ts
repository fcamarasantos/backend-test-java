type tipo_veiculo = {
  id: number,
  tipo: string,
}

type marcas_veiculo = {
  id: number,
  nome: string,
}

export class CreateVeiculoDto {
  readonly id: number;
  readonly placa: string;
  readonly tipo_id: number;
  readonly marca_id: number;
  readonly modelo_id: number;
  readonly cor_id: number
}