import { ApiProperty } from '@nestjs/swagger';
export class CreateVeiculoDto {
  @ApiProperty({ type: String, description: 'Placa como identificador do veiculo.' })
  readonly placa: string;
  @ApiProperty({ type: String, description: 'Tipo do veículo [automovel] ou [motocicleta] ' })
  readonly tipo: string;
  @ApiProperty({ type: String, description: 'Marca do veículo' })
  readonly marca: string;
  @ApiProperty({ type: String, description: 'Modelo do veiculo' })
  readonly modelo: string;
  @ApiProperty({ type: String, description: 'Cor do veiculo' })
  readonly cor: string;
}