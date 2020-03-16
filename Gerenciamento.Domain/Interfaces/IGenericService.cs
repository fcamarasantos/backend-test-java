using Management.Domain.Entities;
using System;
using System.Collections.Generic;
using System.Text;

namespace Management.Domain.Interfaces
{
    public interface IGenericService<T> where T: BaseEntity
    {
        List<T> get(int? id = null);
        T insert(T entity);
        T update(int id, T entity);
        bool delete(int id);
    }
}
