using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Newtonsoft.Json;

namespace news.Models
{
    [Table("news_category")]
    public class Category
    {
        [Key, DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public long Id { get; set; }

        [Column("name")]
        public String Name { get; set; }

        [JsonIgnore]
        public List<NewsCategory> NewsCategory { get; set; }

        public Category()
        {
            NewsCategory = new List<Models.NewsCategory>();
        }
    }
}
