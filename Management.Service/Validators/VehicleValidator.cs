using FluentValidation;
using Management.Domain.Entities;
using System;
using System.Collections.Generic;
using System.Text;

namespace Management.Service.Validators
{
    public class VehicleValidator: AbstractValidator<Vehicle>
    {
        public VehicleValidator()
        {
            RuleFor(v => v)
                .NotNull();

            RuleFor(v => v.color)
                .NotNull().WithMessage("Informe a cor do veículo.")
                .NotEmpty().WithMessage("Informe a cor do veículo.")
                .MaximumLength(30).WithMessage("A cor deve ter no maximo 30 digitos");

            RuleFor(v =>v.id_type_vehicle)
                .NotNull().WithMessage("Informe o id do tipo do veículo.")
                .NotEmpty().WithMessage("Informe o id do tipo do veículo.");

            RuleFor(v => v.license)
                .NotNull().WithMessage("Informe a placa do veículo.")
                .NotEmpty().WithMessage("Informe a placa do veículo.")
                .MaximumLength(30).WithMessage("A placa deve ter no maximo 30 digitos");

            RuleFor(v => v.mark)
                .NotNull().WithMessage("Informe a marca do veículo.")
                .NotEmpty().WithMessage("Informe a marca do veículo.")
                .MaximumLength(30).WithMessage("A marca deve ter no maximo 30 digitos");

            RuleFor(v => v.model)
                .NotNull().WithMessage("Informe o modelo do veículo.")
                .NotEmpty().WithMessage("Informe o modelo do veículo.")
                .MaximumLength(30).WithMessage("O modelo deve ter no maximo 30 digitos");
        }
    }
}
