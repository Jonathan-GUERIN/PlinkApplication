from __future__ import print_function
from __future__ import division
import torch
import torch.nn as nn
import pandas as pd
import os
import glob
import torch.optim as optim
import numpy as np
import torchvision
from torchvision import datasets, models, transforms
import matplotlib.pyplot as plt
import time
import copy
import torch
from torch.utils.data import Dataset,DataLoader
import  cv2
from sklearn.model_selection import train_test_split
import matplotlib.image as mpimg
from tqdm import tqdm_notebook as tqdm
from sklearn.metrics import roc_auc_score
print("PyTorch Version: ",torch.__version__)
print("Torchvision Version: ",torchvision.__version__)
torch.manual_seed(42)
from PIL import Image

# Import matplotlib and configure it for pretty inline plots
import matplotlib.pyplot as plt

def inference(test_image):
    labels = ['classe_0','classe_1','classe_2','classe_3']
    # First prepare the transformations: resize the image to what the model was trained on and convert it to a tensor
    data_transform = transforms.Compose([transforms.Resize((224, 224)), transforms.ToTensor()])
    normalize = transforms.Normalize(mean=[0.485, 0.456, 0.406],std=[0.229, 0.224, 0.225])
    test_transf = transforms.Compose([transforms.ToPILImage(),
                                      transforms.ToTensor(),normalize])
    # Load the image
    image = Image.open(test_image)
    plt.imshow(image), plt.xticks([]), plt.yticks([])
    
    # Now apply the transformation, expand the batch dimension, and send the image to the GPU
    image = data_transform(image)
    #image = test_transf(image).unsqueeze(0).cuda()
    image = test_transf(image).unsqueeze(0)
    # Download the model if it's not there already. It will take a bit on the first run, after that it's fast
    
    
    PATH_model = 'd:/Users/jonat/TelecomParis/PACT/Projet/projet/IA/whole_model_heavy2.pth'
    
    model_ft2 = torch.load(PATH_model)
    #print(model_ft2)
    # Send the model to the GPU 
    #model_ft2.cuda()
    # Set layers such as dropout and batchnorm in evaluation mode
    model_ft2.eval();
    
    # Get the 1000-dimensional model output
    out = model_ft2(image)
    #print(out)
    m = nn.Softmax(dim=1)
    output = m(out)
    #print(output)
    # Find the predicted class
    #print("Predicted class is: {}".format(labels[out.argmax()]))
    if labels[out.argmax()] == 'classe_0':
        return "healthy"
    elif labels[out.argmax()] == 'classe_1':
        return "multiple"
    elif labels[out.argmax()] == 'classe_2':
        return "rust"
    elif labels[out.argmax()] == 'classe_3':
        return "scab"
    return "nope"

print(inference("d:/Users/jonat/TelecomParis/PACT/Projet/projet/IA/data/plaisir12_04/IMG_20220417_170257.jpg"))