using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using news.Models;
using MySql.Data.EntityFrameworkCore.Extensions;

namespace news.ModelConfigurations
{
    public class NewsConfiguration : IEntityTypeConfiguration<Models.News>
    {
        public void Configure(EntityTypeBuilder<News> builder)
        {
            builder
                .Property(n => n.Created)
                .ForMySQLHasDefaultValueSql("CURRENT_TIMESTAMP");
        }
    }
}
