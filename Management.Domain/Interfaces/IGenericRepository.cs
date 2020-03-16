using Management.Domain.Entities;
using System;
using System.Collections.Generic;
using System.Text;

namespace Management.Domain.Interfaces
{
    public interface IGenericRepository<T> where T: BaseEntity
    {
        T insert(T entity);
        T update(int id, T entity);
        bool remove(int id);
        ICollection<T> get(int? id = null);
    }
}
