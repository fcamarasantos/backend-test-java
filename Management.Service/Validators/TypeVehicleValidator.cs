using FluentValidation;
using Management.Domain.Entities;
using System;
using System.Collections.Generic;
using System.Text;

namespace Management.Service.Validators
{
    public class TypeVehicleValidator: AbstractValidator<TypeVehicle>
    {
        public TypeVehicleValidator()
        {
            RuleFor(tv => tv)
                .NotEmpty()
                .OnAnyFailure(ty => { throw new Exception("Type vehicle invalid"); });

            RuleFor(ty => ty.description)
                .NotEmpty().WithMessage("Necessário informar a descricao")
                .NotNull().WithMessage("Necessário informar a descricao")
                .MaximumLength(30);
        }
    }
}
