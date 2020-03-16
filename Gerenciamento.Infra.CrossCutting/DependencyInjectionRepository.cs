using Gerenciamento.Infra.Data.Repository;
using Management.Domain.Interfaces;
using Microsoft.Extensions.DependencyInjection;
using System;
using System.Collections.Generic;
using System.Text;

namespace Gerenciamento.Infra.CrossCutting
{
    public static class DependencyInjectionRepository
    {
        public static void RegisterServices(IServiceCollection services)
        {

        }

        public static void RegisterRepositories(IServiceCollection services)
        {
            services.AddScoped<IGenericRepository, GenericRepository>();
        }
    }
}
